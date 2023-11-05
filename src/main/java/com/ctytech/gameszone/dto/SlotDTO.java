package com.ctytech.gameszone.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SlotDTO {

    private Integer slotId;

    @NotNull(message = "{slot.slotname.absent}")
    @Size(max = 16, min = 2, message = "{slot.slotname.invalid}")
    private String slotName;

    private LocalTime startTime;

    private LocalTime endTime;

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((slotId == null) ? 0 : slotId.hashCode());
        result = prime * result + ((slotName == null) ? 0 : slotName.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SlotDTO other = (SlotDTO) obj;
        if (slotId == null) {
            if (other.slotId != null)
                return false;
        } else if (!slotId.equals(other.slotId))
            return false;
        if (slotName == null) {
            if (other.slotName != null)
                return false;
        } else if (!slotName.equals(other.slotName))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SlotDTO [slotId=" + slotId + ", slotName=" + slotName + ", startTime=" + startTime + ", endTime="
                + endTime + "]";
    }

}
