# blend-modes
Create a composite image from combining two images using blend modes in native Java.

The Blend Modes package enables blending different images, or image layers, by means of blend modes. These modes are commonly found in graphics programs like Adobe Photoshop or GIMP.

Blending through blend modes allows to mix images in a variety of ways. This package currently supports the following 18 blend modes:
- Normal (BlendMode.normal())
- Darken (BlendMode.darken())
- Multiply (BlendMode.multiply())
- Color Burn (BlendMode.colorBurn())
- Linear Burn (BlendMode.linearBurn())
- Lighten (BlendMode.lighten())
- Screen (BlendMode.screen())
- Color Dodge (BlendMode.colorDodge())
- Addition / Linear Dodge (BlendMode.addition())
- Overlay (BlendMode.overlay())
- Soft Light (BlendMode.softLight())
- Hard Light (BlendMode.hardLight())
- Vivid Light (BlendMode.vividLight())
- Linear Light (BlendMode.linearLight())
- Difference (BlendMode.difference())
- Subtract (BlendMode.subtract())
- Divide (BlendMode.divide())

The intensity of blending can be controlled by means of an opacity parameter that is passed into the functions. See Usage for more information.

This package does not require JavaFX. It is built using the Java AWT API only.

## Usage
The blend mode methods take image data expressed as a BufferedImage. An example is provided below for a standard method of creating and inputting BufferedImage parameters.

Input Methods are overloaded. Allowing for method calls with opacity inputs and without. If an opacity value is not inputted, it is assumed to be 1 (100%).
```java
// With Opacity Value
BufferedImage image = BlendMode.screen(bg, fg, opacity);

// Without Opacity Value
BufferedImage image = BlendMode.screen(bg, fg);

```

## Important Considerations
1. Foreground and Background images have to be the same size.
2. Has to be 8 Bit per Channel Image.
3. Opacity is a beta feature. It is not completely accurate in certain blend modes.
## Example

```java
import BlendMode.BlendMode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException {

        // Set Variable Names
        BufferedImage bg = ImageIO.read(new File("background.jpg"));
        BufferedImage fg = ImageIO.read(new File("foreground.jpg"));
        double opacity = .5;

        // Create Composite
        BufferedImage image = BlendMode.screen(bg, fg, opacity);

        // Export Composite
        ImageIO.write(image, "jpg", new File("output.jpg"));
    }
}
```


## Roadmap

- Fix Opacity Feature to work with all blend modes properly
- Provide solution for different image sizes
- Support 16bit and 24bit images

## Images
![bg](https://github.com/aabalke33/blend-modes/assets/22086435/5b9166e2-422f-4684-ac15-67b782237a3d)
![fg](https://github.com/aabalke33/blend-modes/assets/22086435/e6ef2e2d-cf57-4475-9821-0f7fde097fa0)
![normal](https://github.com/aabalke33/blend-modes/assets/22086435/3ad3faea-d21f-46c3-9b3a-525aadd6ffb3)
![darken](https://github.com/aabalke33/blend-modes/assets/22086435/6fd87007-c7fb-4c03-8e5e-a4af68800b77)
![multiply](https://github.com/aabalke33/blend-modes/assets/22086435/36f71dd8-3ba6-4022-8336-9cefeda8bb4d)
![colorburn](https://github.com/aabalke33/blend-modes/assets/22086435/cff96899-3083-4775-bd1c-db57b6e3944e)
![linearburn](https://github.com/aabalke33/blend-modes/assets/22086435/d117009a-1948-4aab-82b2-238c9ec72e56)
![lighten](https://github.com/aabalke33/blend-modes/assets/22086435/f9e774d2-1756-41a9-bc60-abba53ef7bf7)
![screen](https://github.com/aabalke33/blend-modes/assets/22086435/55e179e9-bfd4-467e-895d-c8a959965f02)
![colordodge](https://github.com/aabalke33/blend-modes/assets/22086435/d09347e5-65f3-4c6c-9c79-f4a768c7077f)
![addition](https://github.com/aabalke33/blend-modes/assets/22086435/d73f719f-2fc9-40cf-84c0-8efa1e035369)
![overlay](https://github.com/aabalke33/blend-modes/assets/22086435/c1b58107-ab33-4677-a076-12dbdd481e85)
![softlight](https://github.com/aabalke33/blend-modes/assets/22086435/1b014312-8a25-47fd-9f4f-ef7c94bf7d02)
![hardlight](https://github.com/aabalke33/blend-modes/assets/22086435/61caccd4-a946-41cc-a7af-5be90dae113f)
![vividlight](https://github.com/aabalke33/blend-modes/assets/22086435/83b214fd-ee0f-4f99-a860-ee3715e9aece)
![linearlight](https://github.com/aabalke33/blend-modes/assets/22086435/bfea58e7-4d8d-4424-9798-c30d6895d37e)
![difference](https://github.com/aabalke33/blend-modes/assets/22086435/c2d4d1dc-69ca-43b7-9498-cec5b4a4950f)
![subtract](https://github.com/aabalke33/blend-modes/assets/22086435/9b802a35-0f6e-4602-81fa-409a240b2b21)
![divide](https://github.com/aabalke33/blend-modes/assets/22086435/bbcdba5e-f630-4f7b-a1ca-d22a170c33da)
