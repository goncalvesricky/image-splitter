image-splitter
==============

Visual Cryptography
-We're going to input a message, and create an image of that message.
-Next, we create two new images with identical dimensions as original image -- two layers.
	-We go through each pixel of the original image of the message:
		-If it's white: make one layer's pixel, make the other black (randomize which is which) 
		-If it's black: make both layers' pixel the same (randomize to decide if black or white)
-Output the two image files, both of which are indecipherable.

To recreate image -- program has option to input both image files, and will duly iterate through every pixel of both images (following in reverse the rules set forth above) and will regenerate the original image of the message.
