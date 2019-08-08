try {
    def appName=env.APP_NAME
    def gitSourceUrl=env.GIT_SOURCE_URL
    def gitSourceRef=env.GIT_SOURCE_REF
    def project=""
    node {
        stage("Initialize") {
            project = env.PROJECT_NAME
            echo "appName: ${appName}"
            echo "gitSourceUrl: ${gitSourceUrl}"
            echo "gitSourceUrl: ${gitSourceUrl}"
            echo "gitSourceRef: ${gitSourceRef}"
        }
    }
    node("maven") {
        stage("Checkout") {
            git url: "${gitSourceUrl}", branch: "${gitSourceRef}"
        }
        stage("Build JAR") {
            sh "mvn clean package"
            stash name:"jar", includes:"target/app.jar"
        }
    }
    node {
        stage("Build Image") {
            unstash name:"jar"
            openshift.withCluster() {
                openshift.withProject('cicd') {
                    def builds = openshift.selector("bc", "${appName}-build").related('builds')
                    timeout(5) { 
                        builds.untilEach(1) {
                            return (it.object().status.phase == "Complete")
                        }
                    }
                }
            }
        }
        stage("Deploy DEV") {
            openshift.withCluster() {
                openshift.withProject('cicd') {
                    openshift.tag("${appName}:latest", "${appName}:dev")
                    def dc = openshift.selector('dc', "${appName}")
                    dc.rollout().status()
                }
            }
        }
        stage("Deploy QA") {
            openshift.withCluster() {
                openshift.withProject('cicd') {
                    openshift.tag("${appName}:latest", "${appName}:qa")
                    def dc = openshift.selector('dc', "${appName}")
                    dc.rollout().status()
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
