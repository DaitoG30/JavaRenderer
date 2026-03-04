package RenderEngine;

import Models.RawModel;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader {

    public static RawModel loadOBJModel(String filename,Loader loader){
        FileReader fileReader;

        try{
            fileReader = new FileReader("src/main/res/" + filename + ".obj");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<Vector3f> vertices = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        List<Vector2f> textures = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] texturesArray = null;
        int[] indicesArray = null;

        try{

            while((line = bufferedReader.readLine()) != null){
                String[] lineSplit = line.split(" ");
                if(line.startsWith("v ")){
                    Vector3f vertex = new Vector3f(Float.parseFloat(lineSplit[1]),Float.parseFloat(lineSplit[2]),Float.parseFloat(lineSplit[3]));
                    vertices.add(vertex);
                }else if(line.startsWith("vt ")){
                    Vector2f texture = new Vector2f(Float.parseFloat(lineSplit[1]),Float.parseFloat(lineSplit[2]));
                    textures.add(texture);
                }else if(line.startsWith("vn ")){
                    Vector3f normal = new Vector3f(Float.parseFloat(lineSplit[1]),Float.parseFloat(lineSplit[2]),Float.parseFloat(lineSplit[3]));
                    normals.add(normal);
                }else if(line.startsWith("f ")){
                    texturesArray = new float[vertices.size() * 2];
                    normalsArray = new float[vertices.size() * 3];
                    break;
                }
            }

            while(line !=null){
                if( !line.startsWith("f ") ){
                    line = bufferedReader.readLine();
                    continue;
                }

                String[] lineSplit = line.split(" ");
                String[] vertex1 = lineSplit[1].split("/");
                String[] vertex2 = lineSplit[2].split("/");
                String[] vertex3 = lineSplit[3].split("/");

                processVertex(vertex1,indices,textures,normals,normalsArray,texturesArray);
                processVertex(vertex2,indices,textures,normals,normalsArray,texturesArray);
                processVertex(vertex3,indices,textures,normals,normalsArray,texturesArray);
                line = bufferedReader.readLine();

            }
            fileReader.close();


        }catch(Exception e){
            e.printStackTrace();
        }

        verticesArray = new float[vertices.size()*3];
        indicesArray = new int[indices.size()];

        int vertexIndex = 0;
        for(Vector3f vertex : vertices){
            verticesArray[vertexIndex++] = vertex.x;
            verticesArray[vertexIndex++] = vertex.y;
            verticesArray[vertexIndex++] = vertex.z;
        }

        for (int i=0;i<indices.size();i++){
            indicesArray[i] = indices.get(i);
        }

        System.out.println("Vertices: " + vertices.size());
        System.out.println("Indices: " + indices.size());


        return loader.loadToVAO(verticesArray,texturesArray,normalsArray,indicesArray);
    }


    private static void processVertex(String[] vertexData, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals, float[] normalsArray, float[] texturesArray ){

        int currentVertexPosition = Integer.parseInt(vertexData[0])-1;
        indices.add(currentVertexPosition);
        Vector2f currentTexture = textures.get(Integer.parseInt(vertexData[1])-1);
        texturesArray[currentVertexPosition*2] = currentTexture.x;
        texturesArray[(currentVertexPosition*2)+1] = currentTexture.y;
        Vector3f currentNormal = normals.get(Integer.parseInt(vertexData[2])-1);
        normalsArray[currentVertexPosition*3] = currentNormal.x;
        normalsArray[currentVertexPosition*3+1] = currentNormal.y;
        normalsArray[currentVertexPosition*3+2] = currentNormal.z;

    }

}
