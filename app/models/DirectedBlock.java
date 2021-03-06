package models;

import de.cm.osm2po.model.LatLon;
import de.cm.osm2po.routing.RoutingResultSegment;

public class DirectedBlock {
    private final RoutingResultSegment segment;
    private final Block block;

    public DirectedBlock(RoutingResultSegment segment, Block block) {
        this.segment = segment;
        this.block = block;
    }

    public GeoPoint getStart() {
        LatLon latLon = segment.getLatLons()[0];
        return new GeoPoint(latLon.getLat(), latLon.getLon());
    }

    public GeoPoint getEnd() {
        LatLon [] latLon = segment.getLatLons();
        LatLon end = latLon[latLon.length - 1];
        return new GeoPoint(end.getLat(), end.getLon());
    }

    public int getId() {
        return segment.getId();
    }

    public int getStreetId() {
        return block.getStreet().getId();
    }

    public int getSourceId() {
        return segment.getSourceId();
    }

    public int getTargetId() {
        return segment.getTargetId();
    }

    public boolean isEmpty() {
        return block.isEmpty();
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getId());
        sb.append(": ");
        sb.append(getBlock().getStreet().getOsm_name());
        sb.append(" - ");
        sb.append(getBlock().getPlaces().size());
        sb.append(" places");
        return sb.toString();
    }
}
