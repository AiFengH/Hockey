attribute vec4 a_Position;
attribute vec4 a_Color;
varying vec4 v_Color;
vec4 position;
void main() {
	position = vec4(a_Position.x/9.0, a_Position.y/14.0, a_Position.z, a_Position.w);
	v_Color = a_Color;
    gl_Position = position;
	gl_PointSize = 10.0;
}