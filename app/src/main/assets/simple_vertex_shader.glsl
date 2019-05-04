attribute vec4 a_Position;
attribute vec4 a_Color;
varying vec4 v_Color;
uniform mat4 u_Matrix;
vec4 position;
void main() {
	position = vec4(a_Position.x, a_Position.y, a_Position.z, a_Position.w);
	v_Color = a_Color;
    gl_Position = u_Matrix * a_Position;
	gl_PointSize = 20.0;
}