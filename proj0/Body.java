public class Body {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  private String imgFileName;

  private static final double G = 6.67e-11; // static: cannot be altered, public: available to the outsie world

  public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Body(Body b) {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  public double calcDistance(Body b) {
    double x = xxPos-b.xxPos;
    double y = yyPos-b.yyPos;
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
  }

  public double calcForceExertedBy(Body b) {
    double rSquared = Math.pow(this.calcDistance(b), 2);
    return G*mass*b.mass/rSquared;
  }

  public double calcForceExertedByX(Body b) {
    double dx = b.xxPos - xxPos;
    double r = this.calcDistance(b);
    double F = this.calcForceExertedBy(b);
    return F*dx/r;
  }

  public double calcForceExertedByY(Body b) {
    double dy = b.yyPos - yyPos;
    double r = this.calcDistance(b);
    double F = this.calcForceExertedBy(b);
    return F*dy/r;
  }

  public double calcNetForceExertedByX(Body[] allBodys) {
    double netForce = 0;
    for(Body b : allBodys){
      if (!this.equals(b)){
        netForce += this.calcForceExertedByX(b);
      }
    }
    return netForce;
  }

  public double calcNetForceExertedByY(Body[] allBodys) {
    double netForce = 0;
    for (Body b : allBodys) {
      if (!this.equals(b)){
        netForce += this.calcForceExertedByY(b);
      }
    }
    return netForce;
  }

  public void update(double dt, double xForce, double yForce) {
    double xxAcc = xForce / mass;
    double yyAcc = yForce / mass;
    xxVel = xxVel + dt * xxAcc;
    yyVel = yyVel + dt * yyAcc;
    xxPos = xxPos + dt * xxVel;
    yyPos = yyPos + dt * yyVel;
  }

  public void draw() {
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }
}
