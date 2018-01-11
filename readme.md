# CK_21
The 21st year of Krunch. For the game FIRST Power Up.

## How to begin
1. Install [Eclipse](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/oxygen/R2/eclipse-inst-win64.exe) or a different IDE if you know what you're doing. The rest of the instruction are for Eclipse.
2. When Eclipse launches, go to Window>Perspective>Open Perspective>Other... and double click on Git. From now on when I reference the Git Perspective, click the yellow "Git" button in the top right. The Java Perspective is the one next to it.
3. Click File>Import... and select Git>Projects from Git.
4. Copy this link: https://github.com/FRC79/CK_21.git
5. Click Clone URI and enter your GitHub account info into the boxes on the bottom. Select Store in Secure Store to not have to type in your account info all the time.
6. Click Next until asked how to import the project. Import it as an Eclipse project.
7. Finish
8. In the Java perspective, CK_21 will be where all the code is located.

## How to grab updates
**It's important to do this often to make sure you are up to date and prevent conflicts.**
1. Open the Git Perspective.
2. Select CK_21>Remotes and right click on origin and select Fetch.
3. If it prompts that you did in fact receive something new continue. Otherwise, you're up to date.
4. Go to CK_21>Branches>Local and change to the branch that had changes (double click).
5. Under Remote Tracking, right click on the same branch and select merge.
6. If there are conflicts, see the software lead to help resolve those.

## How to make and upload changes
1. Create a new branch (if you aren't already working and one; **DO NOT MAKE CHANGES IN MASTER!**) by opening the Git Perspective and selecting CK_21>Branches>Local.
2. Right click on Local and select Switch To>New Branch...
3. Select master as the source and rename it to whatever you'll be working on (e.g. cube-manipulator)
4. Switch to Java Perspective and do whatever to the code.
5. Go back to the Git Perspective and select the Git Staging tab in the bottom right frame.
6. By right clicking on each file under Unstaged Changes and selecting Add to Index, group related changes together and create a short but descriptive message of what was changes/added in the box to the right. Click Commit when done.
7. Repeat step 6 for all items in Unstaged Changes.
8. Click Commit and Push on the last commit, or select CK_21>Remotes and right click on origin and select Push.
9. If there are no errors, you're done. If you are satisfied with your work, submit a Pull Request via the GitHub web interface.

Pushing your code is the only way to get it saved onto GitHub, otherwise it could be lost if it gets deleted somehow. **Be sure to push your code when you finish for the day even when it's not complete to avoid losing data.**