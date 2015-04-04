An OpenGL Boilerplate
===========

###A minimal OpenGL boilerplate to be built with Gradle.  Compiles with Java 1.7

1. Clone the Repo!  
`git clone https://github.com/CptSpaceToaster/OpenGL-boiler.git`  
2. Run the project! (this will install everything locally)  
On 'nix  
`./gradlew run`  
On Windows  
`./gradlew.bat run`  
3. TODO: How on earth am I supposed to release/package this?  
The jar that's assembled expects the OpenGL Natives to be installed locally in the a folder in "java.library.path"  I think this can be solved by manually setting everything up, but as of right now, there's no "automatic installation" of the OpenGL Natives.  I'll have to go through licensing on OpenGL to figure out what's possible.  I think a wrapper script/launcher that downloads and installes OpenGL is the common setup.
