Heres a quick guide to get the prototype up and running:

Note that this was tried on Mac OS X, though NetBeans is very cross platform, so you should not have any problems doing this on other platforms.

You will need the latest stable version of NetBeans (7.2.1) from netbeans.org, the Java EE edition, or the one with everything in it.

- Check out the prototype branch from the repository using your SVN client. You will need the whole N03 folder from the branch.
- In NetBeans, open the N03 project from the working copy.
- Once everything is loaded, click the Services tab on the left of the window.
- Expand Databases, right click on Java DB and select Create Database.
- The database name, username and password all needs to be "monstermash" (without the quotes). Click OK.
- Go back to the Projects tab.
- Select N03, then click Run.

That should be it. Your web browser should automatically load the app, but if not it should be at http://localhost:8080/N03.

jab41@aber.ac.uk