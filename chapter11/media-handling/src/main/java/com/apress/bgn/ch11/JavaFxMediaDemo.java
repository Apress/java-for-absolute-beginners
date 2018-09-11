/*
Freeware License, some rights reserved

Copyright (c) 2018 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.ch11;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class JavaFxMediaDemo extends Application {
    final static int option = 3;
    //0 display normal image
    //1 display scaled
    //2 display rotated
    //3 onclick resize

    public static void main(String... args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFX Image Demo");
        final File src = new File("chapter11/media-handling/src/main/resources/cover.png");
        final Image image = new Image(new FileInputStream(src));

        StackPane root = new StackPane();

        final ImageView imageView = new ImageView(image);
        switch (option) {
            case 0:
                imageView.setFitHeight(image.getHeight());
                imageView.setFitWidth(image.getWidth());
                imageView.setPreserveRatio(true);
                root.getChildren().add(imageView);
                primaryStage.setScene(new Scene(root, image.getWidth()+10, image.getHeight()+10));
                break;
            case 1:
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                root.getChildren().add(imageView);
                primaryStage.setScene(new Scene(root, 120, 120));
                break;
            case 2:
                Rectangle2D viewportRect = new Rectangle2D(2, 2, 600, 600);
                imageView.setViewport(viewportRect);
                imageView.setRotate(90);
                root.getChildren().add(imageView);
                primaryStage.setScene(new Scene(root, 620, 620));
                break;
            case 3:
                imageView.setFitHeight(image.getHeight());
                imageView.setFitWidth(image.getWidth());
                imageView.setPreserveRatio(true);
                root.getChildren().add(imageView);
                imageView.setPickOnBounds(true);
                imageView.setOnMouseClicked(mouseEvent -> {
                    if(imageView.getFitWidth() > 100) {
                        imageView.setFitWidth(100);
                        imageView.setPreserveRatio(true);
                        imageView.setSmooth(true);
                    } else {
                        imageView.setFitHeight(image.getHeight());
                        imageView.setFitWidth(image.getWidth());
                        imageView.setPreserveRatio(true);
                    }
                });
                primaryStage.setScene(new Scene(root, image.getWidth()+10, image.getHeight()+10));
                break;
        }





        primaryStage.show();
    }
}
