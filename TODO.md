TO DO
=====

* fix background rendering for when camera is near edge and world size is big
* implement Thing-Block collisions
* when non-static Thing collides with a static Thing, speed should be set to 0 in the correct axis and non-static Thing should be moved to edge of static Thing
* add variable animation speed
* Separate different visual Things into classes
  * make a ColorThing (extends Thing and contains a Color)
  * separate ImageMob into ImageMob and ImageThing
  * make a TextureThing (extends Thing and contains a Animation)
