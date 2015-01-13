TO DO
=====

* make width & height private in the World class
* fix background rendering for when camera is near edge and world size is big
* implement Block collisions
* sensor should be colliding if they exit the world bounds / touch a Block / touch a Thing
* when Thing collides, speed should be set to 0 in the correct axis
* remove timer methods/variables from World class
* add variable animation speed
* Separate different visual Things
  * make a ColorThing (extends Thing and contains a Color)
  * make a ImageThing (extends Thing and contains a BufferedImage)
  * make a TextureThing (extends Thing and contains a Animation)
  * separate ImageMob into ImageMob and ImageThing
