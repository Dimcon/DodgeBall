DodgeBall
=========
As the name suggests, dodge the balls! 

Code is in Core/src/com/Dimcon/

Main Class -> Dodgeball.java

###Encampased is the beginnings of the Naos LibGDX Add-on.

Rect.java

ResMan.java

Screen.java

ScreenManager.java

TouchHandler.java

  Naos includes:
- Screens (Naos handles interface switching better than anything)
  - Simply create a few classes that extend Screen, Give them names in their OnCreate methods, implement the Create,AnimIn,Draw,AnimOut and Destroy methods. In your main class, declare all your Screen classes as variables and then declare the first screen with ScreenManager.FirstScreen(FirstScreenHere);
  - Easily switch screens using ScreenManager.Switch("sNewScreenName"); from within any screen. This handles the lifecycle of screens automatically.
  - You can manually control a screens lifecycle by changing its CycleStage value. Or return True from a CycleStage callback to move to the next one.
  - Screens are drawn inside it's local rDisplay. 
  - To accomodate for changes in the screens scale and position, an fX/fYUnit is used (float X/Y Unit) A single fX/YUnit is 01% of the screens width/height and is used for positioning.  eg. (23.26f x fYUnit) is equivalent to 23.26% of the screens height. The units are calculated per cycle to accomodate for animations of rDisplay (and thus the Screen).

- New Rect[angle] class:
  - Bottom, top, left and right (b,t,l,r) for clarity
  - Rect.Draw(Texture, SpriteBatch) -> Use the Rect as parameters for Drawing
  - Integrated with TouchHandler to greatly simplify Touch 
    handling (Will determine if the touch started in the Rect (Istouched)
    or if the pointer is just passing through the Rect (IsTouching) .)
  - Animation is as easy as end postion, acceleration and Time to take: 
  * rRect.StartAnimT(*Rect*, *Interpolator*, *float*);
  - Contains Rudimentary methods such as MoveLeft(214.62f); CenterX / Y(); IsInside(rRect); <(Collision test) And can even become a square bound by another Rect -> rWillBeSquare.CopySquare(rRectangle);
  - Has a static debug swich that will draw only the outlines of every Rect when Draw is called;

- ResMan <- Resource Manager!
  - Simple as ResMan.Add(String Name, String sPathToTexture);
  - And tTexture = ResMan.Get(String Name);
  - Supports adding textures with 'Groups'. Whole groups can be disposed at a time. 
  - Work In Progress.
  - Will support Disposing textures when memory is low. Starting with large, unused textures.
  - Will support using a separate thread to load images in the background.

- Finally, TouchHandler!
  - Accepts Gdx Input events through InputProcessor.
  - Distributes events to Scene2D Stage to allow button and actor touches.
  - Stores pointer positions and where they first touched seperately to determine where touch events should be locked to.
  - Although still in progress, TouchHandler only supports touch events through Rects and Scene2D Stage.


##STILL IN PROGRESS

