# Focust - Installation & Set-up Guide
This Markdown file is a guide of how to install the dependencies and set up both the front-end and back-end servers for this web application.

Obviously, this document assumes that you have already downloaded the repository from GitHub. All of the directories will be based on the main project directory.

## Pre-requisites
To be able to run the program, one must have installed [**Docker**]() and [**Jenkins**](https://www.jenkins.io/), with *Jenkins* being run a linux-based Docker container or machine. 

I recommend using my custom `jenkins/jenkins:lts-jdk17` Docker image on [my GitHub](https://github.com/allandeboe/jenkins-jdk17), as it contains all of the needed plugins and settings to run the `Jenkinsfile` and I use this Jenkins image when using Jenkins for my project with no issues. Also, it is in dark mode by default so you never have to subject your eyeballs to burning white light. 😉

> [!NOTE]
> You might want to make modifications to the host port for the back-end server inside the `Jenkinsfile` for your fork of the project as the back-end server runs on port `8080` by default - the same as *Jenkins*

## Setting up Jenkins
After having Jenkins properly set up, you will need to log in to Jenkins (*of course!*), and make sure you either have a Linux-based Jenkins slave agent or use the Built-in Node (you will need to change the settings if you are using my custom Jenkins image, as my [custom Jenkins image](https://github.com/allandeboe/jenkins-jdk17) automatically sets the number of executors of the Built-in Node to `0`) available to run the Jenkinsfile.

### Creating Credentials for MySQL & Spring Security
You will need to create two, Global "Username-and-password" credentials with the ids `focust-mysql-database` and `focust-spring-security`, each will handle the credentials for the MySQL database and Spring Security, respectively. If you are unsure how to go about this, please reference [*this page*](https://www.jenkins.io/doc/book/using/using-credentials/) on the Jenkins website.

![Example Credentials Page](./references/jenkins-creating-credentials-example.png)

### Creating a Job that Builds & Runs the Project!
You will need to create a new job, name it `focust-web-application` (or whatever makes sense to you) and select "**Pipeline**". After that, select **OK**.

![Image showcasing the first page when creating a new job, which consists of creating a title for the job as well as selecting job type.](./references/jenkins-creating-new-job-part-1.png)

Next, go all the way to where it says `Pipeline` and change the *definition* to "**Pipeline script from SCM**", choose **Git** as the *SCM* and them put in the repository URL of your fork of the project (in my case, `https://github.com/allandeboe/Focust-Web-App.git`). You might also want to have GitHub credentials stored in your Jenkins image if your fork is private, as authentication might be needed.

![Image showcasing the 'Pipeline' section and what needs to be filled out.](./references/jenkins-creating-new-job-part-2.png)

Finally, click **Save**, which will lead you to the page of the new job. After that, press **Build Now** to build and run the project! 

![Image the page of the job, with "Build Now" being highlighted in yellow](./references/jenkins-building-application.png)

The build takes a while to load (that might change in future commits!), so be patient. You can see the progress made on the build by going to the "**Build History**" part of the page and clicking on the build number.

![Image of the "Build History" section, with the text "#1" being highlighted in yellow](./references/jenkins-build-history.png)

You can also see the progress when you go back to the dashboard:

![Image of the Jenkins Dashboard, with Jenkins building the application](./references/jenkins-dashboard.png)

From here, you can tell if the build is done if there is if the loading bars under the **Build Executor Status** disappear entirely. If all goes well, you should see green checkmark next to the job once refreshing the page.

![Image of the dashboard with the successful build of the project, marked with a green checkmark](./references/jenkins-dashboard-successful-build.png)

### Viewing the Application
Once all of the containers are set up, you can go to `http://localhost:3000` (Assuming you have Docker and run Jenkins in a container, like I do) to see the site in action!

![View of the Focust website at http://localhost:3000](./references/localhost-3000-view.png)