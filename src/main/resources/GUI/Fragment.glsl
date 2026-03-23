#version 400 core

in vec2 pass_textureCoords;

out vec4 out_color;

uniform sampler2D guiTexture;

void main(void){

    out_color = texture(guiTexture,pass_textureCoords);

}
