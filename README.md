# Simple drawing app

This app reads instructions from a text file then generates an SVG output file.

## Possibble instructions:
- add_line_segments <x0> <y0> <x1> <y1> [<xn> <yn>...]
- add_circle <centre_x> <centre_y> <radius>
- add_rectangle <corner_x> <corner_y> <width> <height>
- new_group
- translate <dx> <dy>
- flip_vertical <axis>
- flip_horizontal <axis>
- merge

This was a homework project for a Java class. The write function of the Circle, PolyLine and Rectangle classes were written for us.

