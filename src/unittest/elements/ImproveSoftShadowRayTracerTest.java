/**
 * 
 */
package unittest.elements;


import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.BasicRayTracer;
import renderer.ImageWriter;
import renderer.ImproveSoftShadowRayTracer;
import renderer.Render;
import scene.Scene;

class ImproveSoftShadowRayTracerTest {
	/**
	 * Test method for
	 */
	@org.junit.Test
    public void SoftShadow() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVpSize(200, 200).setVpDistance(1000);
        scene.geometries.add( //
                new Sphere(new Point3D(30, 0, -150), 50) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKr(0.1).setKs(0.4).setShininess(80)) //
                , new Plane(new Point3D(30, -60, -150), new Vector(0, 1, 0)) //
                        .setEmission(new Color(20, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKr(0.4).setShininess(80))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 0), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0), new Point3D(0, 0, 50), new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("softShadow", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveSoftShadowRayTracer(scene).setRADIUS(100));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void diamondSoftShadow() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(1.5, 0.5, -2), new Vector(-0.5, -0.25, 1), new Vector(0, 1, 0.25)) //
                .setVpSize(10, 10).setVpDistance(5);
        Point3D J = new Point3D(0.04, -1.5, 1.17);
        Point3D A = new Point3D(-0.58962, 0, 0.77765);
        Point3D B = new Point3D(-0.61367, 0, 1.56646);///
        Point3D C = new Point3D(0, 0, 2);
        Point3D D = new Point3D(0, 0, 0.36);
        Point3D E = new Point3D(0.70034, 0, 0.78047);
        Point3D F = new Point3D(0.6836, 0, 1.62883);
        Point3D I = new Point3D(0.34, 0, 1.81);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D(0.69, 0, 1.2);
        Point3D R = new Point3D(0.35, 0, 0.57);//0.35
        Point3D S = new Point3D(-0.29, 0, 0.57);
        Point3D T = new Point3D(-0.6, 0, 1.17);
        Point3D U = new Point3D(-0.31, 0, 1.78);
        Material material = new Material().setKd(0.5).setKs(0.4).setShininess(80).setKr(0).setKt(0);
        scene.geometries = new Geometries(
                new Triangle(J, E, F)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(J, E, D)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(J, A, D)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(J, A, B)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(J, C, B)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(J, C, F)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(Q, N, F)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(Q, N, M)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(Q, E, M)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(R, E, M)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(R, L, M)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(R, L, D)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(S, L, D)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(S, L, K)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(S, A, K)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(T, A, K)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(T, P, K)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(T, P, B)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(U, B, P)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(U, P, O)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(U, C, O)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(I, C, O)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Triangle(I, N, O)
                        .setEmission(new Color(100, 100, 150)).setMaterial(material),
                new Triangle(I, N, F)
                        .setEmission(new Color(100, 100, 100)).setMaterial(material),
                new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                        .setMaterial(material)
        );

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new PointLight(new Color(200, 100, 100), new Point3D(-2, 20, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("diamondSoftShadow", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void diamondSoftShadow2() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(1.5, 0.5, -2), new Vector(-0.5, -0.25, 1), new Vector(0, 1, 0.25)) //
                .setVpSize(10, 10).setVpDistance(5);
        Point3D J = new Point3D(0.04, -1.5, 1.17);
        Point3D A = new Point3D(-0.58962, 0, 0.77765);
        Point3D B = new Point3D(-0.61367, 0, 1.56646);///
        Point3D C = new Point3D(0, 0, 2);
        Point3D D = new Point3D(0, 0, 0.36);
        Point3D E = new Point3D(0.70034, 0, 0.78047);
        Point3D F = new Point3D(0.6836, 0, 1.62883);
        Point3D I = new Point3D(0.34, 0, 1.81);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D(0.69, 0, 1.2);
        Point3D R = new Point3D(0.35, 0, 0.57);//0.35
        Point3D S = new Point3D(-0.29, 0, 0.57);
        Point3D T = new Point3D(-0.6, 0, 1.17);
        Point3D U = new Point3D(-0.31, 0, 1.78);
        Material material = new Material().setKd(0.4).setKs(0).setShininess(80).setKr(0.4).setKt(0);
        for (int i = 0; i < 1; i++) {

            scene.geometries.add(new Geometries(
                    new Triangle(J, U, B)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, B, T)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, A, T)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, A, S)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, S, D)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, D, R)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, E, R)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, E, Q)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, Q, F)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, F, I)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, I, C)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, C, U)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(Q, N, F)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(Q, N, M)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(Q, E, M)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(R, E, M)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(R, L, M)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(R, L, D)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(S, L, D)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(S, L, K)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(S, A, K)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(T, A, K)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(T, P, K)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(T, P, B)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(U, B, P)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(U, P, O)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(U, C, O)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(I, C, O)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(I, N, O)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(I, N, F)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material)
//                    new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
//                            .setMaterial(material)

            ));
            Vector vector = new Vector(1.5, 0, 3);
            J = J.add(vector);
            A = A.add(vector);
            B = B.add(vector);
            C = C.add(vector);
            D = D.add(vector);
            E = E.add(vector);
            F = F.add(vector);
            I = I.add(vector);
            K = K.add(vector);
            L = L.add(vector);
            M = M.add(vector);
            N = N.add(vector);
            O = O.add(vector);
            P = P.add(vector);
            Q = Q.add(vector);
            R = R.add(vector);
            S = S.add(vector);
            T = T.add(vector);
            U = U.add(vector);
        }
        scene.geometries.add(new Geometries(new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                        .setMaterial(material)),
                new Geometries(new Plane(new Point3D(-2.3, -2.3, 2.65), new Vector(1, 0.25, -1))
                        .setMaterial(material)),
                new Geometries(new Plane(new Point3D(2.3, 2.41, 1.8), new Vector(-1.0625, 0.25, -1))
                        .setMaterial(material)));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new PointLight(new Color(300, 300, 300), new Point3D(-2, 20, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("diamondSoftShadow1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveSoftShadowRayTracer(scene).setRADIUS(4));
        render.renderImage();
        render.writeToImage();
    }
@Test
    public void diamondSoftShadow3() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(1.5, 0.5, -2), new Vector(-0.5, -0.25, 1), new Vector(0, 1, 0.25)) //
                .setVpSize(10, 10).setVpDistance(5);
        Point3D J = new Point3D(0.04, -1.5, 1.17);
        Point3D A = new Point3D(-0.58962, 0, 0.77765);
        Point3D B = new Point3D(-0.61367, 0, 1.56646);///
        Point3D C = new Point3D(0, 0, 2);
        Point3D D = new Point3D(0, 0, 0.36);
        Point3D E = new Point3D(0.70034, 0, 0.78047);
        Point3D F = new Point3D(0.6836, 0, 1.62883);
        Point3D I = new Point3D( 0.4, 0,1.94);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D( 0.81, 0,1.19);
        Point3D R = new Point3D( 0.44, 0,0.46);//0.35
        Point3D S = new Point3D( -0.36, 0,0.45);
        Point3D T = new Point3D(-0.74, 0, 1.17);
        Point3D U = new Point3D( -0.38, 0,1.9);
        Material material = new Material().setKd(0.4).setKs(0).setShininess(80).setKr(0.4).setKt(0);
        for (int i = 0; i < 1; i++) {

            scene.geometries.add(new Geometries(
                    new Triangle(J, U, B)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, B, T)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, A, T)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, A, S)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, S, D)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, D, R)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, E, R)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, E, Q)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, Q, F)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, F, I)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, I, C)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, C, U)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(Q, N, F)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(Q, N, M)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(Q, E, M)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(R, E, M)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(R, L, M)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(R, L, D)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(S, L, D)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(S, L, K)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(S, A, K)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(T, A, K)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(T, P, K)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(T, P, B)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(U, B, P)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(U, P, O)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(U, C, O)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(I, C, O)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(I, N, O)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(I, N, F)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material)
//                    new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
//                            .setMaterial(material)

            ));
            Vector vector = new Vector(1.5, 0, 3);
            J = J.add(vector);
            A = A.add(vector);
            B = B.add(vector);
            C = C.add(vector);
            D = D.add(vector);
            E = E.add(vector);
            F = F.add(vector);
            I = I.add(vector);
            K = K.add(vector);
            L = L.add(vector);
            M = M.add(vector);
            N = N.add(vector);
            O = O.add(vector);
            P = P.add(vector);
            Q = Q.add(vector);
            R = R.add(vector);
            S = S.add(vector);
            T = T.add(vector);
            U = U.add(vector);
        }
        scene.geometries.add(new Geometries(new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                        .setMaterial(material)),
                new Geometries(new Plane(new Point3D(-2.3, -2.3, 2.65), new Vector(1, 0.25, -1))
                        .setMaterial(material)),
                new Geometries(new Plane(new Point3D(2.3, 2.41, 1.8), new Vector(-1.0625, 0.25, -1))
                        .setMaterial(material)));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new PointLight(new Color(300, 300, 300), new Point3D(-2, 20, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new SpotLight(new Color(300, 300, 300), new Point3D(1.5, -1, -8),new Vector(-1.1, 0, 8)) //
                        .setKl(0.0001).setKq(0.00001));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("realisticDiamondGlossy", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

}