package unittest.elements;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class for integration tests between the formation of beams from a camera along the calculation of cuts of a beam with geometric bodies
 */
public class CameraIntegrationTest {
    /**
     * Checks whether the shape has the correct number of intsersection points
     * @param geometry the shape
     * @param camera
     * @param intsersectionNum the number of points that intersection with the shape
     */
	public void TestCamera(Geometry geometry,Camera camera,int intsersectionNum){
        List<Point3D> allPoints=null;
        //goes over every pixel in the view plane and add the Intersection points between the ray from the pixel to the shape
        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, j, i);

                List<Point3D> list = geometry.findIntersections(ray);
                if(list!=null){
                    if(allPoints==null){
                        allPoints=new LinkedList<>();
                    }
                    allPoints.addAll(list);
                }
            }
        }
        //if there are not intersection with the shape then count=0
        int count=0;
        if(allPoints!=null) {
            count=allPoints.size();
        }
        assertEquals("wrong number of Intersection",count,intsersectionNum);
    }

    /**
     * Test in intersection with camera and sphere
     */
    @Test
    public void testSphereCamera(){
        //Two intersection points, the sphere is the view plane
        Sphere sphere=new Sphere(new Point3D(0,0,-3),1);

        Camera camera=new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0)).setVpDistance(1).setVpSize(3,3);
        TestCamera(sphere,camera,2);

        //18 intersection points, the sphere intersection the view plane
        sphere=new Sphere(new Point3D(0,0,-2.5),2.5);
        camera=new Camera(new Point3D(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0)).setVpDistance(1).setVpSize(3,3);
        TestCamera(sphere,camera,18);

        //10 intersection points, the sphere intersection the view plane
        sphere=new Sphere(new Point3D(0,0,-2),2);
        camera=new Camera(new Point3D(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0)).setVpDistance(1).setVpSize(3,3);
        TestCamera(sphere,camera,10);

        //9 intersection points
        camera=new Camera(Point3D.ZERO,new Vector(0, 0, -1), new Vector(0, -1, 0)).setVpDistance(1).setVpSize(3,3);
        sphere=new Sphere(new Point3D(0,0,-2),4);
        TestCamera(sphere,camera,9);

        //Zero intersection points,the sphere before the view plane
        sphere=new Sphere(new Point3D(0,0,1),0.5);
        TestCamera(sphere,camera,0);
    }

    /**
     * test plane with camera
     */
    @Test
    public void testPlaneCamera() {
        Camera camera=new Camera(new Point3D(0,0,2),new Vector(0, 0, -1), new Vector(0, -1, 0)).setVpDistance(1).setVpSize(3,3);

        //9 intersection points,the plane parallel to the view plane
        Plane plane=new Plane(new Point3D(0,1,0),new Point3D(-1,0,0),new Point3D(1,0,0));
        TestCamera(plane,camera,9);

        //9 intersection points
        camera=new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVpDistance(1).setVpSize(3,3);
        plane=new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 2));
        TestCamera(plane,camera,9);

        //6 intersection points
        plane=new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1));
        TestCamera(plane,camera,6);
    }

    /**
     * test triangle with camera
     */
    @Test
    public void testTriangleCamera() {
        Camera  camera=new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVpDistance(1).setVpSize(3,3);

        //One intersection point
        Triangle triangle=new Triangle(new Point3D(0, 1, -2),new Point3D(1, -1, -2),new Point3D(-1, -1, -2));
        TestCamera(triangle,camera,1);

        //Two intersection point
        triangle=new Triangle(new Point3D(0, 20, -2),new Point3D(1, -1, -2),new Point3D(-1, -1, -2));
        TestCamera(triangle,camera,2);
    }
}