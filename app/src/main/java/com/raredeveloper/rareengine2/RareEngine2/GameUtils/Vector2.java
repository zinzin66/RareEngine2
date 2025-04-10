package RareEngine2.GameUtils;


public class Vector2 {
    private float x;
    private float y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y; // No inversion anymore
    }

    public Vector2(Vector2 v2) {
        this.x = v2.x;
        this.y = v2.y; // Direct copy
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY(){
        return y;
    }

    public void setY(float y) {
        this.y = y; // No inversion anymore
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y; // No inversion anymore
    }

    public static Vector2 getFromString(String str) {
        String[] parts = str.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid string format. Expected format: 'x,y'");
        }
        float x = Float.parseFloat(parts[0].trim());
        float y = Float.parseFloat(parts[1].trim());
        return new Vector2(x, y);
    }

    public float distance(Vector2 other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public Vector2 add(Vector2 other) {
        this.x += other.x;
        this.y += other.y; // No inversion anymore
        return this;
    }

    public Vector2 subtract(Vector2 other) {
        this.x -= other.x;
        this.y -= other.y; // No inversion anymore
        return this;
    }

    public Vector2 multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2 multiply(float sx, float sy) {
        this.x *= sx;
        this.y *= sy;
        return this;
    }

    public Vector2 divide(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    public Vector2 divide(float sx, float sy) {
        this.x /= sx;
        this.y /= sy;
        return this;
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2 normalized() {
        float mag = magnitude();
        if (mag == 0) return new Vector2(0, 0);
        return new Vector2(x / mag, y / mag); // No inversion anymore
    }

    public float dot(Vector2 other) {
        return this.x * other.x + this.y * other.y;
    }

    public Vector2 scale(float scalar) {
        return new Vector2(this.x * scalar, this.y * scalar); // No inversion anymore
    }

    public Vector2 lerp(Vector2 other, float t) {
        return new Vector2(
            this.x + (other.x - this.x) * t,
            this.y + (other.y - this.y) * t // No inversion anymore
        );
    }

    public void reorder() {
        if (y > x) {
            float temp = x;
            x = y;
            y = temp;
        }
    }

    @Override
    public String toString() {
        return x + "," + y; // No inversion anymore
    }

    public static float doProduct(Vector2 v1, Vector2 v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }
	public static double angleBetween(Vector2 v1, Vector2 v2) {
		double angle1 = Math.atan2(-v1.y, v1.x);
		double angle2 = Math.atan2(-v2.y, v2.x);
		double angle = angle2 - angle1;

		// Normalize to [-PI, PI]
		if (angle > Math.PI) angle -= 2 * Math.PI;
		if (angle < -Math.PI) angle += 2 * Math.PI;

		return angle; // In radians
	}
}
