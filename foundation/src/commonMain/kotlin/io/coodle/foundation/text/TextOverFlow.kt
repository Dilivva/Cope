package io.coodle.foundation.text

/**
 * Text overflow
 *
 * @constructor Create empty Text overflow
 */
interface TextOverflow{

    //TODO: add functionality
    companion object{
        val Clip = 1
        val Ellipsis = 2
        val Visible = object: TextOverflow{}
    }
}