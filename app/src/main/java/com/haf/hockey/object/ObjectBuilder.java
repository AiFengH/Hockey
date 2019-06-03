package com.haf.hockey.object;

/**
 * Created by Aaron Mt on 2019/5/4.
 */
public class ObjectBuilder {

    public static final int FLOAT_PER_VERTEX = 3;
    private float[] mVertexData;
    private float mOffset = 0;

    /**
     *
     * @param sizeInVertices 顶点个数
     */
    private ObjectBuilder(int sizeInVertices) {
        mVertexData = new float[sizeInVertices * FLOAT_PER_VERTEX];
    }

    /**
     * 圆柱体顶点的个数 （）
     * @param numPoint  圆弧顶点个数
     * @return
     */
    public static int sizeOfOpenCylinderInVertices(int numPoint) {
        return (numPoint + 1) * 2;
    }
}
