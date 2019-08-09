try {
    def appName=env.APP_NAME
    def gitSourceUrl=env.GIT_SOURCE_URL
    def gitSourceRef=env.GIT_SOURCE_REF
    def project=""
    node("maven") {
        stage("Initialize") {
            project = env.PROJECT_NAME
            echo "appName: ${appName}"
            echo "gitSourceUrl: ${gitSourceUrl}"
            echo "gitSourceUrl: ${gitSourceUrl}"
            echo "gitSourceRef: ${gitSourceRef}"
        }
        stage("Checkout") {
            git url: "${gitSourceUrl}", branch: "${gitSourceRef}"
        }
        stage("Build JAR") {
            sh "mvn clean package"
            stash name:"jar", includes:"target/app.jar"
        }
        stage("Build Image") {
            unstash name:"jar"
            sh "oc start-build ${appName}-build --from-file=target/app.jar -n ${project} --follow"
        }
        stage("Tag DEV") {
            openshift.withCluster() {
                openshift.withProject('cicd') {
                    openshift.tag("${appName}:latest", "${appName}:dev")
                }
            }
        }
        stage("Deploy DEV") {
            openshift.withCluster() {
                openshift.withProject('app-dev') {
                    def deploymentsExists = openshift.selector( "dc", "${appName}").exists()
                    if (!deploymentsExists) {
                            echo "Deployments do not yet exist.  Create the environment."
                            def models = openshift.process( "cicd//demo-app-frontend-template", "-p", "IMAGE_TAG=dev" )
                            def created = openshift.create( models )
                            echo "Created: ${models.names()}"
                    } else {
                        echo "Deploying to DEV."
                        def dc = openshift.selector('dc', "${appName}")
                        dc.rollout().status()
                    }
                }            
            }
        }
        stage("Tag for QA") {
            openshift.withCluster() {
                openshift.withProject('cicd') {
                    openshift.tag("${appName}:dev", "${appName}:qa")
                }
            }
        }
        stage("Deploy QA") {
            openshift.withCluster() {
                openshift.withProject('app-qa') {
                    def deploymentsExists = openshift.selector( "dc", "${appName}").exists()
                    if (!deploymentsExists) {
                            echo "Deployments do not yet exist.  Create the environment."
                            def models = openshift.process( "cicd//demo-app-frontend-template", "-p", "IMAGE_TAG=qa" )
                            def created = openshift.create( models )
                            echo "Created: ${models.names()}"
                    } else {
                        echo "Deploying to QA."
                        def dc = openshift.selector('dc', "${appName}")
                        dc.rollout().status()
                    }
                }            
            }
        }
    }
} catch (err) {
    echo "in catch block"
    echo "Caught: ${err}"
    currentBuild.result = 'FAILURE'
    throw err
}
