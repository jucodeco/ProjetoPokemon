package com.example.pokemon

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class ColorTypeTest {



    @Test
    fun `test get color` (){
        Assert.assertEquals(R.color.colorfire, ColorType.getcolortype("fire"))
        Assert.assertEquals(R.color.colorpoison, ColorType.getcolortype("poison"))
        Assert.assertEquals(R.color.colorflying, ColorType.getcolortype("flying"))
        Assert.assertEquals(R.color.colorwater, ColorType.getcolortype("water"))
        Assert.assertEquals(R.color.colorgrass, ColorType.getcolortype("grass"))
        Assert.assertEquals(R.color.colorbug, ColorType.getcolortype("bug"))
        Assert.assertEquals(R.color.colornormal, ColorType.getcolortype("normal"))
        Assert.assertEquals(R.color.colorelectric, ColorType.getcolortype("electric"))
        Assert.assertEquals(R.color.colorground, ColorType.getcolortype("ground"))
        Assert.assertEquals(R.color.colorfairy, ColorType.getcolortype("fairy"))
        Assert.assertEquals(R.color.colorfighting, ColorType.getcolortype("fighting"))
        Assert.assertEquals(R.color.colorpsychic, ColorType.getcolortype("psychic"))
        Assert.assertEquals(R.color.colorrock, ColorType.getcolortype("rock"))
        Assert.assertEquals(R.color.colorice, ColorType.getcolortype("ice"))
        Assert.assertEquals(R.color.colorghost, ColorType.getcolortype("ghost"))
        Assert.assertEquals(R.color.colordragon, ColorType.getcolortype("dragon"))
        Assert.assertEquals(R.color.colordark, ColorType.getcolortype("dark"))


    }
}