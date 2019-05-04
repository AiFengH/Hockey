attribute vec4 a_Position;
attribute vec2 a_TextureCoordinates;
uniform mat4 u_Matrix;
varying vec2 v_TextureCoordinates;
vec4 position;
void main() {
	v_TextureCoordinates = a_TextureCoordinates;
	position = vec4(a_Position.x, a_Position.y, a_Position.z, a_Position.w);
    gl_Position = u_Matrix * position;
	gl_PointSize = 10.0;
}