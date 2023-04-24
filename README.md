# Aesthetic-Color-Reduction

Takes in a picture, and aesthetically approximates colours to try to immitate drawing

To achieve this goal, this Project uses 3 Transformations:

Blur Blurs the image with variable blur size

Reduction Each Pixel in this image is approximated to the nearest of 6 colors in either black and white mode or in color

Aesthetic Clustering To reduce graininess in the final result, aesthetic clustering removes high contrast pixels

The default combination of these transformations is Blur, Reduction and lastly Aesthetic Clustering (BRC)
