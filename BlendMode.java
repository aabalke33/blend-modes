package BlendMode;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This file provides the ability to crate a composite image by combining two images using blend modes.
 */
public class BlendMode {
    /**
     * The Constants in Mode define the changes to the blend Method based on which Blend Mode is used.
     */
    private enum Mode {
        NORMAL {
            /**
             * Method to provide outline of Normal Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                return (int) Math.round(limitRange(dest));
            }
        },
        DARKEN {
            /**
             * Method to provide outline of Darken Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                return Math.min(src, dest);
            }
        },
        MULTIPLY {
            /**
             * Method to provide outline of Multiply Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;

                // Blend Mode Calculation
                double result = (destPercentage * srcPercentage) * 255;

                return (int) Math.round(limitRange(result));
            }
        },
        COLOR_BURN {
            /**
             * Method to provide outline of Color Burn Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;
                double result;

                // Blend Mode Calculation
                if (destPercentage == 0) {
                    result = 0;
                } else {
                    result = (1 - ((1 - srcPercentage) / destPercentage)) * 255;
                }

                return (int) Math.round(limitRange(result));
            }
        },
        LINEAR_BURN {
            /**
             * Method to provide outline of Linear Burn Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;

                // Blend Mode Calculation
                double result = (srcPercentage + destPercentage - 1) * 255;

                return (int) Math.round(limitRange(result));
            }
        },
        LIGHTEN {
            /**
             * Method to provide outline of Lighten Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                return Math.max(src, dest);
            }
        },
        SCREEN {
            /**
             * Method to provide outline of Screen Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;

                // Blend Mode Calculation
                double result = (1 - ((1 - srcPercentage) * (1 - destPercentage))) * 255;

                return (int) Math.round(limitRange(result));
            }
        },
        COLOR_DODGE {
            /**
             * Method to provide outline of Color Dodge Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;
                double result;

                // Blend Mode Calculation
                if (destPercentage >= 1) {
                    result = destPercentage * 255;
                } else {
                    result = (srcPercentage / (1 - destPercentage)) * 255;
                }

                return (int) Math.round(limitRange(result));
            }
        },
        ADDITION {
            /**
             * Method to provide outline of Addition Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;

                // Blend Mode Calculation
                double result = (srcPercentage + destPercentage) * 255;

                return (int) Math.round(limitRange(result));
            }
        },
        OVERLAY {
            /**
             * Method to provide outline of Overlay Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;
                double result;

                // Blend Mode Calculation
                if (srcPercentage < 0.5) {
                    result = (destPercentage * srcPercentage * 2) * 255;
                } else {
                    result = (1 - ( 2 * (1 - srcPercentage) * (1 - destPercentage))) * 255;
                }

                return (int) Math.round(limitRange(result));
            }
        },
        SOFT_LIGHT {
            /**
             * Method to provide outline of Soft Light Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;

                // Blend Mode Calculation
                double result = ((1 - (2 * destPercentage)) * Math.pow(srcPercentage, 2)
                        + (2 * destPercentage * srcPercentage)) * 255;

                return (int) Math.round(limitRange(result));
            }
        },
        HARD_LIGHT {
            /**
             * Method to provide outline of Hard Light Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;
                double result;

                // Blend Mode Calculation
                if (destPercentage < 0.5) {
                    result = (destPercentage * srcPercentage * 2) * 255;
                } else {
                    result = (1 - ( 2 * (1 - srcPercentage) * (1 - destPercentage))) * 255;
                }

                return (int) Math.round(limitRange(result));
            }
        },
        VIVID_LIGHT {
            /**
             * Method to provide outline of Vivid Light Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;
                double result;

                // Blend Mode Calculation
                if (destPercentage <= 0.5) {
                    result = (1 - ((1 - srcPercentage) / (destPercentage))) * 255;
                } else {
                    result = (srcPercentage / (1 - destPercentage)) * 255;
                }


                return (int) Math.round(limitRange(result));
            }
        },
        LINEAR_LIGHT {
            /**
             * Method to provide outline of Linear Light Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;
                double result;

                // Blend Mode Calculation
                if (destPercentage <= 0.5) {
                    result = (srcPercentage + destPercentage - 1) * 255;
                } else {
                    result = (srcPercentage + destPercentage) * 255;
                }

                return (int) Math.round(limitRange(result));
            }
        },
        DIFFERENCE {
            /**
             * Method to provide outline of Difference Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;
                double result;

                // Blend Mode Calculation
                if (srcPercentage == destPercentage) {
                    result = destPercentage * 255;
                } else if (srcPercentage > destPercentage) {
                    result = (srcPercentage - destPercentage) * 255;
                } else {
                    result = (destPercentage - srcPercentage) * 255;
                }

                return (int) Math.round(limitRange(result));
            }
        },
        SUBTRACT {
            /**
             * Method to provide outline of Subtract Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;
                double result;
                // Blend Mode Calculation
                if (srcPercentage == destPercentage) {
                    result = destPercentage * 255;
                } else {
                    result = (srcPercentage - destPercentage) * 255;
                }

                return (int) Math.round(limitRange(result));
            }
        },
        DIVIDE {
            /**
             * Method to provide outline of Divide Blend Mode calculation.
             * @param src The 8 bit background channel value. (0 -255)
             * @param dest The 8 bit foreground channel value. (0 -255)
             * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
             * @return The 8 bit composite result after blending calculation
             */
            public int blend(int src, int dest, double opacity) {

                dest = opacityProcessing(dest, opacity);

                double srcPercentage = src / 255.0;
                double destPercentage = dest / 255.0;
                // Blend Mode Calculation
                double result = (srcPercentage / destPercentage) * 255;

                return (int) Math.round(limitRange(result));
            }
        };

        /**
         * Abstract Method to provide outline of blend calculation for each Mode.
         * @param src The 8 bit background channel value. (0 -255)
         * @param dest The 8 bit foreground channel value. (0 -255)
         * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
         * @return The 8 bit composite result after blending calculation
         */
        public abstract int blend(int src, int dest, double opacity);
    }

    /**
     * Method to create a composite image from two images using a Normal Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage normal(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.NORMAL;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Darken Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage darken(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.DARKEN;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Multiply Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage multiply(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.MULTIPLY;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Color Burn Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage colorBurn(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.COLOR_BURN;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Linear Burn Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage linearBurn(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.LINEAR_BURN;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Lighten Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage lighten(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.LIGHTEN;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Screen Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage screen(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.SCREEN;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Color Dodge Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage colorDodge(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.COLOR_DODGE;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using an Addition (Linear Dodge) Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage addition(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.ADDITION;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using an Overlay Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage overlay(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.OVERLAY;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Soft Light Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage softLight(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.SOFT_LIGHT;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Hard Light Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage hardLight(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.HARD_LIGHT;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Vivid Light Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage vividLight(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.VIVID_LIGHT;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Linear Light Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage linearLight(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.LINEAR_LIGHT;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Difference Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage difference(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.DIFFERENCE;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Subtract Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage subtract(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.SUBTRACT;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Method to create a composite image from two images using a Divide Blend Mode.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage divide(BufferedImage bg, BufferedImage fg, double opacity) {
        BlendMode.Mode mode = BlendMode.Mode.DIVIDE;
        return processing(bg,fg,opacity, mode);
    }
    /**
     * Overloaded method of Normal Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage normal(BufferedImage bg, BufferedImage fg) {
        return normal(bg, fg, 1);
    }
    /**
     * Overloaded method of Darken Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage darken(BufferedImage bg, BufferedImage fg) {
        return darken(bg, fg, 1);
    }
    /**
     * Overloaded method of Multiply Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage multiply(BufferedImage bg, BufferedImage fg) {
        return multiply(bg, fg, 1);
    }
    /**
     * Overloaded method of Color Burn Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage colorBurn(BufferedImage bg, BufferedImage fg) {
        return colorBurn(bg, fg, 1);
    }
    /**
     * Overloaded method of Linear Burn Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage linearBurn(BufferedImage bg, BufferedImage fg) {
        return linearBurn(bg, fg, 1);
    }
    /**
     * Overloaded method of Lighten Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage lighten(BufferedImage bg, BufferedImage fg) {
        return lighten(bg, fg, 1);
    }
    /**
     * Overloaded method of Screen Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage screen(BufferedImage bg, BufferedImage fg) {
        return screen(bg, fg, 1);
    }
    /**
     * Overloaded method of Color Dodge Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage colorDodge(BufferedImage bg, BufferedImage fg) {
        return colorDodge(bg, fg, 1);
    }
    /**
     * Overloaded method of Addition (Linear Dodge) Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage addition(BufferedImage bg, BufferedImage fg) {
        return addition(bg, fg, 1);
    }
    /**
     * Overloaded method of Overlay Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage overlay(BufferedImage bg, BufferedImage fg) {
        return overlay(bg, fg, 1);
    }
    /**
     * Overloaded method of Soft Light Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage softLight(BufferedImage bg, BufferedImage fg) {
        return softLight(bg, fg, 1);
    }
    /**
     * Overloaded method of Hard Light Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage hardLight(BufferedImage bg, BufferedImage fg) {
        return hardLight(bg, fg, 1);
    }
    /**
     * Overloaded method of Vivid Light Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage vividLight(BufferedImage bg, BufferedImage fg) {
        return vividLight(bg, fg, 1);
    }
    /**
     * Overloaded method of Linear Light Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage linearLight(BufferedImage bg, BufferedImage fg) {
        return linearLight(bg, fg, 1);
    }
    /**
     * Overloaded method of Difference Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage difference(BufferedImage bg, BufferedImage fg) {
        return difference(bg, fg, 1);
    }
    /**
     * Overloaded method of Subtract Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage subtract(BufferedImage bg, BufferedImage fg) {
        return subtract(bg, fg, 1);
    }
    /**
     * Overloaded method of Divide Blend Mode. If an Opacity Parameter is not inputted, an opacity of 1 (100%) is assumed.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @return Returns a BufferedImage Composite.
     */
    public static BufferedImage divide(BufferedImage bg, BufferedImage fg) {
        return divide(bg, fg, 1);
    }

    /**
     * Method to create composite image from blending background and foreground image.
     * @param bg The image used as the background of the composite image. The foreground image is layered above.
     * @param fg The image used as the foreground of the composite image. The background image is layered below.
     * @param opacity Opacity Percentage of the foreground image (0 100% Transparent - 1 100% Opaque)
     * @param mode The mode being used for processing.
     * @return BufferedImage outputted after blend mode processing
     */
    private static BufferedImage processing(BufferedImage bg, BufferedImage fg, double opacity, BlendMode.Mode mode) {
        Graphics2D g2d = bg.createGraphics();

        // Create a new BufferedImage to store the result
        BufferedImage resultImage = new BufferedImage(bg.getWidth(), bg.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        // Splits image into Pixels and then ARGB Channels for each pixel (24 Bit Pixels, 8 Bit Channels (0 - 255)
        // Applies Blend Mode to each channel and adds new composite pixel to resultImage
        for (int x = 0; x < bg.getWidth(); x++) {
            for (int y = 0; y < bg.getHeight(); y++) {

                int srcPixel = bg.getRGB(x, y);
                int destPixel = fg.getRGB(x, y);

                int srcAlpha = (srcPixel >> 24) & 0xFF;
                int destAlpha = (destPixel >> 24) & 0xFF;

                int srcRed = (srcPixel >> 16) & 0xFF;
                int destRed = (destPixel >> 16) & 0xFF;

                int srcGreen = (srcPixel >> 8) & 0xFF;
                int destGreen = (destPixel >> 8) & 0xFF;

                int srcBlue = srcPixel & 0xFF;
                int destBlue = destPixel & 0xFF;

                int resultRed = mode.blend(srcRed, destRed, opacity);
                int resultGreen = mode.blend(srcGreen, destGreen, opacity);
                int resultBlue = mode.blend(srcBlue, destBlue, opacity);
                int resultAlpha = mode.blend(srcAlpha, destAlpha, opacity);

                int resultPixel =  (resultAlpha << 24) | (resultRed << 16) | (resultGreen << 8) | resultBlue;
                resultImage.setRGB(x, y, resultPixel);
            }
        }
        g2d.drawImage(resultImage, 0, 0, null);
        g2d.dispose();

        return bg;
    }
    /**
     * Method to change dest channel value based on input opacity
     * @param dest 8-Bit Channel Value from Foreground Pixel (0 - 255)
     * @param opacity Opacity Percentage (0 100% Transparent - 1 100% Opaque)
     * @return Returns new Destination 8 Bit Channel Value ( 0 - 255)
     */
    private static int opacityProcessing(int dest, double opacity) {
        return (int) (128 - (128 * opacity) + (dest * opacity));
    }
    /**
     * Clamps Channel Values to 8 Bits (0 - 255)
     * @param result 8-Bit Channel Value after blend mode processing
     * @return 8-Bit Channel Value clamped to 8 Bits (0 - 255)
     */
    private static double limitRange(double result) {
        if (result > 255) {
            result = 255;
        } else if (result < 0) {
            result = 0;
        }
        return result;
    }
}
}
