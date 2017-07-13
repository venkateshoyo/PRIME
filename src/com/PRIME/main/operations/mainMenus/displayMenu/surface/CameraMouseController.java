package com.PRIME.main.operations.mainMenus.displayMenu.surface;

/**
 * Created by hkban on 7/1/2017.
 */
import java.awt.event.*;
import java.util.Iterator;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.controllers.camera.AbstractCameraController;
import org.jzy3d.chart.controllers.mouse.MouseUtilities;
import org.jzy3d.chart.controllers.thread.camera.CameraThreadController;
import org.jzy3d.maths.BoundingBox3d;
import org.jzy3d.maths.Coord2d;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Scale;
import org.jzy3d.plot3d.primitives.axes.AxeBox;
import org.jzy3d.plot3d.rendering.view.View;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class CameraMouseController extends AbstractCameraController implements MouseListener, MouseMotionListener, MouseWheelListener {
    protected Coord2d prevMouse;
    protected CameraThreadController threadController;
    protected Scale scaleX;
    protected Scale scaleY;
    protected Scale scaleZ;


    public CameraMouseController() {
    }

    public CameraMouseController(Chart chart) {
        if(this.threadController != null) {
            this.threadController.stop();
            this.threadController = null;
        }
        this.register(chart);
        scaleZ = chart().getView().getScale();
         scaleX = new org.jzy3d.maths.Scale(chart().getView().getBounds().getXmin(), chart().getView().getBounds().getXmax());
        scaleY = new org.jzy3d.maths.Scale(chart().getView().getBounds().getYmin(), chart().getView().getBounds().getYmax());

    }

    public void register(Chart chart) {
        super.register(chart);
        this.prevMouse = Coord2d.ORIGIN;
        chart.getCanvas().addMouseListener(this);
        chart.getCanvas().addMouseMotionListener(this);
        chart.getCanvas().addMouseWheelListener(this);
    }

    public void dispose() {
        Iterator i$ = this.targets.iterator();

        while(i$.hasNext()) {
            Chart c = (Chart)i$.next();
            c.getCanvas().removeMouseListener(this);
            c.getCanvas().removeMouseMotionListener(this);
            c.getCanvas().removeMouseWheelListener(this);
        }

        if(this.threadController != null) {
            this.threadController.stop();
        }

        super.dispose();
    }

    public void addSlaveThreadController(CameraThreadController controller) {
        this.removeSlaveThreadController();
        this.threadController = controller;
    }

    public void removeSlaveThreadController() {
        if(this.threadController != null) {
            this.threadController.stop();
            this.threadController = null;
        }

    }

    public void mousePressed(MouseEvent e) {
        if(!this.handleSlaveThread(e)) {
            this.prevMouse.x = (float)e.getX();
            this.prevMouse.y = (float)e.getY();
        }
    }

    public boolean handleSlaveThread(MouseEvent e) {
        if(MouseUtilities.isDoubleClick(e) && this.threadController != null) {
            this.threadController.start();
            return true;
        } else {
            if(this.threadController != null) {
                this.threadController.stop();
            }

            return false;
        }
    }


    public void mouseDragged(MouseEvent e) {
        if(this.threadController != null) {
            this.threadController.stop();
            this.threadController = null;
        }
        Coord2d mouse = new Coord2d((float)e.getX(), (float)e.getY());

        Coord2d move;
        if(MouseUtilities.isLeftDown(e)) {
            move = mouse.sub(this.prevMouse).div(100.0F);
            this.rotate(move);
        } else if(MouseUtilities.isRightDown(e)) {
            move = mouse.sub(this.prevMouse);
            if(e.isControlDown()) {
                if (move.x != 0.0F) {
                    Scale current = new org.jzy3d.maths.Scale(chart().getView().getBounds().getXmin(), chart().getView().getBounds().getXmax());
                    Scale newScale = current.add((double) (move.x / 10000.0F));
                    // this.setScale(newScale, updateView);
                    BoundingBox3d bounds = chart().getView().getBounds();
                    bounds.setXmin((float) newScale.getMin());
                    bounds.setXmax((float) newScale.getMax());
                    chart().getView().setBoundManual(bounds);
                    chart().getView().shoot();

                }
            }
               else if (e.isAltDown()) {
                    if (move.x != 0.0F) {

                        Scale current1 = new org.jzy3d.maths.Scale(chart().getView().getBounds().getYmin(), chart().getView().getBounds().getYmax());
                        Scale newScale1 = current1.add((double) (-move.y / 3000.0F));
                        // this.setScale(newScale, updateView);
                        BoundingBox3d bounds1 = chart().getView().getBounds();
                        bounds1.setYmin((float) newScale1.getMin());
                        bounds1.setYmax((float) newScale1.getMax());
                        chart().getView().setBoundManual(bounds1);

                        chart().getView().shoot();
                    }
                }
            else if(move.y != 0.0F) {
                this.shift(move.y / 500.0F);
                 }
            }
        this.prevMouse = mouse;
        }

    public void mouseWheelMoved(MouseWheelEvent e) {
//        if(this.threadController != null) {
//            this.threadController.stop();
//        }

        float factor = 1.0F + (float) e.getWheelRotation() / 10.0F;

        if (e.isControlDown()) {
            this.zoomX(factor);
        } else if (e.isAltDown()) {
            this.zoomY(factor);
        } else if (e.isShiftDown()) {
//            double rangeX = (double) (chart().getView().getBounds().getXmax() - chart().getView().getBounds().getXmin());
//            Scale scaleX = null;
//            if (rangeX > 0.0D) {
//                double centerX = (double) ((chart().getView().getBounds().getXmax() + chart().getView().getBounds().getXmin()) / 2.0F);
//                double minX = centerX + ((double) chart().getView().getBounds().getXmin() - centerX) * (double) factor;
//                double maxX = centerX + ((double) chart().getView().getBounds().getXmax() - centerX) * (double) factor;
//                if (minX < maxX) {
//                    scaleX = new Scale(minX, maxX);
//                } else if (factor < 1.0F) {
//                    scaleX = new Scale(centerX, centerX);
//                }
//            }
//            Scale scaleY = null;
//            double rangeY = (double) (chart().getView().getBounds().getYmax() - chart().getView().getBounds().getYmin());
//            if (rangeY > 0.0D) {
//                double centerY = (double) ((chart().getView().getBounds().getYmax() + chart().getView().getBounds().getYmin()) / 2.0F);
//                double minY = centerY + ((double) chart().getView().getBounds().getYmin() - centerY) * (double) factor;
//                double maxY = centerY + ((double) chart().getView().getBounds().getYmax() - centerY) * (double) factor;
//                if (minY < maxY) {
//                    scaleY = new Scale(minY, maxY);
//                } else if (factor < 1.0F) {
//                    scaleY = new Scale(centerY, centerY);
//                }
//            }
//            Scale scaleZ = null;
//            double rangeZ = (double) (chart().getView().getBounds().getZmax() - chart().getView().getBounds().getZmin());
//            if (rangeZ > 0.0D) {
//                double centerZ = (double) ((chart().getView().getBounds().getZmax() + chart().getView().getBounds().getZmin()) / 2.0F);
//                double minZ = centerZ + ((double) chart().getView().getBounds().getZmin() - centerZ) * (double) factor;
//                double maxZ = centerZ + ((double) chart().getView().getBounds().getZmax() - centerZ) * (double) factor;
//                if (minZ < maxZ) {
//                    scaleZ = new Scale(minZ, maxZ);
//                } else if (factor < 1.0F) {
//                    scaleZ = new Scale(centerZ, centerZ);
//                }
//            }
//            BoundingBox3d bounds = chart().getView().getBounds();
//                if (scaleX != null&&scaleY!=null&&scaleZ!=null) {
//
//                    bounds.setXmin((float) scaleX.getMin());
//                    bounds.setXmax((float) scaleX.getMax());
//                    bounds.setYmin((float) scaleY.getMin());
//                    bounds.setYmax((float) scaleY.getMax());
//                    bounds.setZmin((float) scaleZ.getMin());
//                    bounds.setZmax((float) scaleZ.getMax());
//                }
//                chart().getView().setAxe(new AxeBox(bounds));
            this.zoomZ(factor);

            } else {

            this.zoomZ(factor);
            this.zoomX(factor);
            this.zoomY(factor);

            }

        }


    public void mouseClicked(MouseEvent e) {
        if(MouseUtilities.isDoubleClick(e)) {
//            BoundingBox3d bounds = chart().getView().getBounds();
//            bounds.setYmin((float) scaleY.getMin());
//            bounds.setYmax((float) scaleY.getMax());
//            bounds.setXmin((float) scaleX.getMin());
//            bounds.setXmax((float) scaleX.getMax());
//            bounds.setZmin((float) scaleZ.getMin());
//            bounds.setZmax((float) scaleZ.getMax());
//
//            chart().getView().setBoundManual(bounds);
//            chart().getView().shoot();
        }

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }
}

