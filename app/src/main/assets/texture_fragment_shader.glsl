precision mediump float;

//uniform vec4 u_Color;
//varying vec4 v_Color;
uniform sampler2D u_TextureUnit;
varying vec2 v_TextureCoordinates;
void main() {
    //gl_FragColor = v_Color;
	gl_FragColor = texture2D(u_TextureUnit, v_TextureCoordinates);
}