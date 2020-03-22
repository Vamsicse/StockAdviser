package com.galaxy.StockAdviser.constants;

/**
 * @author Vamsi Krishna Myalapalli
 * @since 12/27/2019
 */
public enum MessageConstants {

    DEBUG{
        @Override
        public String toString() {
            return "[DEBUG] ";
        }
    }, ERROR{
        @Override
        public String toString() {
            return "[ERROR] ";
        }
    }, INFO{
        @Override
        public String toString() {
            return "[INFO] ";
        }
    }, WARNING{
        @Override
        public String toString() {
            return "[WARNING] ";
        }
    };
}
