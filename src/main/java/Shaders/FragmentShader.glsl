#version 400 core

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightNormal;

out vec4 out_color;

uniform sampler2D textureSampler;
uniform vec3 lightColor;

void main(void){

    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightNormal);

    float nDot1 = dot(unitNormal,unitLightVector);
    float brightness = max(nDot1,0.2);
    vec3 diffuse = brightness * lightColor;

    out_color = vec4(diffuse,1.0) * texture(textureSampler,pass_textureCoords);

}
