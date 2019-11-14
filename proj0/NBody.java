public class NBody {

  public static double readRadius(String fileName) {
    In in = new In(fileName);
    int noPlanets = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readBodies(String fileName) {
    In in = new In(fileName);

    int noPlanets = in.readInt();
    double radius = in.readDouble();

    Planet[] bodies = new Planet[noPlanets];

    for (int i = 0; i < noPlanets; i++) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();

      bodies[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
    }

    return bodies;
  }

  public static void main(String[] args) {
    double T = Double.valueOf(args[0]);
    double dt = Double.valueOf(args[1]);
    String fileName = args[2];

    double radius = readRadius(fileName);
    Planet[] bodies = readBodies(fileName);

    int noBodies = bodies.length;

    StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		//StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

    for (Planet b : bodies) {
      b.draw();
    }


		StdDraw.show();


    for (int time = 0; time <= T; time += dt){
      double[] xForces = new double[noBodies];
      double[] yForces = new double[noBodies];;
      for (int i = 0; i < noBodies; i++) {
        xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
      for (int i = 0; i < noBodies; i++) {
        bodies[i].update(dt, xForces[i], yForces[i]);
        bodies[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
    }

    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodies.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
    }

  }

}
