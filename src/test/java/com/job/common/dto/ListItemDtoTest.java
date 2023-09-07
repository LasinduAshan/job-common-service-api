package com.job.common.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ListItemDtoTest {

    @Test
    void testCanEqual() {
        assertFalse((new ListItemDto("Value", "Label", true)).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        ListItemDto listItemDto = new ListItemDto("Value", "Label", true);
        assertTrue(listItemDto.canEqual(new ListItemDto("Value", "Label", true)));
    }

    @Test
    void testConstructor() {
        ListItemDto actualListItemDto = new ListItemDto("Value", "Label", true);
        actualListItemDto.setIsNotAvailable(true);
        actualListItemDto.setLabel("Label");
        actualListItemDto.setValue("Value");
        String actualToStringResult = actualListItemDto.toString();
        assertTrue(actualListItemDto.getIsNotAvailable());
        assertEquals("Label", actualListItemDto.getLabel());
        assertEquals("ListItemDto(value=Value, label=Label, isNotAvailable=true)", actualToStringResult);
    }

    @Test
    void testEquals() {
        assertNotEquals(new ListItemDto("Value", "Label", true), null);
        assertNotEquals(new ListItemDto("Value", "Label", true), "Different type to ListItemDto");
    }

    @Test
    void testEquals2() {
        ListItemDto listItemDto = new ListItemDto("Value", "Label", true);
        assertEquals(listItemDto, listItemDto);
        int expectedHashCodeResult = listItemDto.hashCode();
        assertEquals(expectedHashCodeResult, listItemDto.hashCode());
    }
    @Test
    void testEquals3() {
        ListItemDto listItemDto = new ListItemDto("Value", "Label", true);
        ListItemDto listItemDto2 = new ListItemDto("Value", "Label", true);

        assertEquals(listItemDto, listItemDto2);
        int expectedHashCodeResult = listItemDto.hashCode();
        assertEquals(expectedHashCodeResult, listItemDto2.hashCode());
    }

    @Test
    void testEquals4() {
        ListItemDto listItemDto = new ListItemDto(42, "Label", true);
        assertNotEquals(listItemDto, new ListItemDto("Value", "Label", true));
    }

    @Test
    void testEquals5() {
        ListItemDto listItemDto = new ListItemDto(null, "Label", true);
        assertNotEquals(listItemDto, new ListItemDto("Value", "Label", true));
    }

    @Test
    void testEquals6() {
        ListItemDto listItemDto = new ListItemDto("Value", "Value", true);
        assertNotEquals(listItemDto, new ListItemDto("Value", "Label", true));
    }

    @Test
    void testEquals7() {
        ListItemDto listItemDto = new ListItemDto("Value", null, true);
        assertNotEquals(listItemDto, new ListItemDto("Value", "Label", true));
    }

    @Test
    void testEquals8() {
        ListItemDto listItemDto = new ListItemDto("Value", "Label", false);
        assertNotEquals(listItemDto, new ListItemDto("Value", "Label", true));
    }

    /**
     * Method under test: {@link ListItemDto#equals(Object)}
     */
    @Test
    void testEquals9() {
        ListItemDto listItemDto = new ListItemDto("Value", "Label", null);
        assertNotEquals(listItemDto, new ListItemDto("Value", "Label", true));
    }
    @Test
    void testEquals10() {
        ListItemDto listItemDto = new ListItemDto(null, "Label", true);
        ListItemDto listItemDto2 = new ListItemDto(null, "Label", true);

        assertEquals(listItemDto, listItemDto2);
        int expectedHashCodeResult = listItemDto.hashCode();
        assertEquals(expectedHashCodeResult, listItemDto2.hashCode());
    }
    @Test
    void testEquals11() {
        ListItemDto listItemDto = new ListItemDto("Value", null, true);
        ListItemDto listItemDto2 = new ListItemDto("Value", null, true);

        assertEquals(listItemDto, listItemDto2);
        int expectedHashCodeResult = listItemDto.hashCode();
        assertEquals(expectedHashCodeResult, listItemDto2.hashCode());
    }

    @Test
    void testEquals12() {
        ListItemDto listItemDto = new ListItemDto("Value", "Label", null);
        ListItemDto listItemDto2 = new ListItemDto("Value", "Label", null);

        assertEquals(listItemDto, listItemDto2);
        int expectedHashCodeResult = listItemDto.hashCode();
        assertEquals(expectedHashCodeResult, listItemDto2.hashCode());
    }
    @Test
    void testEquals13() {
        ListItemDto listItemDto = new ListItemDto(new ListItemDto("Value", "Label", true), "Label", true);
        assertNotEquals(listItemDto, new ListItemDto(null, "Label", true));
    }
}

