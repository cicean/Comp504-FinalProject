/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package gov.nasa.worldwind.util;

import gov.nasa.worldwind.*;
import gov.nasa.worldwind.event.*;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.globes.Globe;

import java.awt.*;

/**
 * @author Patrick Murris
 * @version $Id: BasicDragger.java 1171 2013-02-11 21:45:02Z dcollins $
 */
public class BasicDragger implements SelectListener
{
    private final WorldWindow wwd;
    private boolean dragging = false;
    private boolean useTerrain = true;

    private Point dragRefCursorPoint;
    private Vec4 dragRefObjectPoint;
    private double dragRefAltitude;

    public BasicDragger(WorldWindow wwd)
    {
        if (wwd == null)
        {
            String msg = Logging.getMessage("nullValue.WorldWindow");
            Logging.logger().severe(msg);
            throw new IllegalArgumentException(msg);
        }

        this.wwd = wwd;
    }

    public BasicDragger(WorldWindow wwd, boolean useTerrain)
    {
        if (wwd == null)
        {
            String msg = Logging.getMessage("nullValue.WorldWindow");
            Logging.logger().severe(msg);
            throw new IllegalArgumentException(msg);
        }

        this.wwd = wwd;
        this.setUseTerrain(useTerrain);
    }

    public boolean isUseTerrain()
    {
        return useTerrain;
    }

    public void setUseTerrain(boolean useTerrain)
    {
        this.useTerrain = useTerrain;
    }

    public boolean isDragging()
    {
        return this.dragging;
    }

    public void selected(SelectEvent event)
    {
        if (event == null)
        {
            String msg = Logging.getMessage("nullValue.EventIsNull");
            Logging.logger().severe(msg);
            throw new IllegalArgumentException(msg);
        }

        if (event.getEventAction().equals(SelectEvent.DRAG_END))
        {
            this.dragging = false;
            event.consume();
        }
        else if (event.getEventAction().equals(SelectEvent.DRAG))
        {
            DragSelectEvent dragEvent = (DragSelectEvent) event;
            Object topObject = dragEvent.getTopObject();
            if (topObject == null)
                return;

            if (!(topObject instanceof Movable))
                return;

            Movable dragObject = (Movable) topObject;
            View view = wwd.getView();
            Globe globe = wwd.getModel().getGlobe();

            // Compute dragged object ref-point in model coordinates.
            // Use the Icon and Annotation logic of elevation as offset above ground when below max elevation.
            Position refPos = dragObject.getReferencePosition();
            if (refPos == null)
                return;

            Vec4 refPoint = globe.computePointFromPosition(refPos);

            if (!this.isDragging())   // Dragging started
            {
                // Save initial reference points for object and cursor in screen coordinates
                // Note: y is inverted for the object point.
                this.dragRefObjectPoint = view.project(refPoint);
                // Save cursor position
                this.dragRefCursorPoint = dragEvent.getPreviousPickPoint();
                // Save start altitude
                this.dragRefAltitude = globe.computePositionFromPoint(refPoint).getElevation();
            }

            // Compute screen-coord delta since drag started.
            int dx = dragEvent.getPickPoint().x - this.dragRefCursorPoint.x;
            int dy = dragEvent.getPickPoint().y - this.dragRefCursorPoint.y;

            // Find intersection of screen coord (refObjectPoint + delta) with globe.
            double x = this.dragRefObjectPoint.x + dx;
            double y = event.getMouseEvent().getComponent().getSize().height - this.dragRefObjectPoint.y + dy - 1;
            Line ray = view.computeRayFromScreenPoint(x, y);
            Position pickPos = null;
            // Use intersection with sphere at reference altitude.
            Intersection inters[] = globe.intersect(ray, this.dragRefAltitude);
            if (inters != null)
                pickPos = globe.computePositionFromPoint(inters[0].getIntersectionPoint());

            if (pickPos != null)
            {
                // Intersection with globe. Move reference point to the intersection point,
                // but maintain current altitude.
                Position p = new Position(pickPos, dragObject.getReferencePosition().getElevation());
                dragObject.moveTo(p);
            }
            this.dragging = true;
            event.consume();
        }
    }
}