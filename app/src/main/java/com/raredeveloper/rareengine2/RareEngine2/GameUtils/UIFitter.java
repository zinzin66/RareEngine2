package RareEngine2.GameUtils;

public class UIFitter {
    // Calculate position on the real screen
    public static Vector2 getPosition(Vector2 fakeScreenSize, Vector2 realScreenSize, Vector2 fakePosition) {
        if (fakeScreenSize != null && realScreenSize != null && fakePosition != null) {
            float percentX = fakePosition.getX() / fakeScreenSize.getX() * 100;
            float percentY = fakePosition.getY() / fakeScreenSize.getY() * 100;
            return new Vector2(percentX / 100 * realScreenSize.getX(), percentY / 100 * realScreenSize.getY());
        } else {
            return new Vector2(); // Default position (0, 0)
        }
    }

    // Calculate the scale for the real screen based on fakeScreenScale
    public static Vector2 getRealScreenScale(Vector2 fakeScreenSize, Vector2 realScreenSize, Vector2 fakeScreenScale) {
        if (fakeScreenSize != null && realScreenSize != null && fakeScreenScale != null) {
            float scaleX = (realScreenSize.getX() / fakeScreenSize.getX()) * fakeScreenScale.getX();
            float scaleY = (realScreenSize.getY() / fakeScreenSize.getY()) * fakeScreenScale.getY();
            return new Vector2(scaleX, scaleY); // Adjusted real screen scale
        } else {
            return new Vector2(1.0f, 1.0f); // Default scale (no scaling)
        }
    }
}
