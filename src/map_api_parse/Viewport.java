
package map_api_parse;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Viewport {

    @Expose
    private Northeast_ northeast;
    @Expose
    private Southwest_ southwest;

    /**
     * 
     * @return
     *     The northeast
     */
    public Northeast_ getNortheast() {
        return northeast;
    }

    /**
     * 
     * @param northeast
     *     The northeast
     */
    public void setNortheast(Northeast_ northeast) {
        this.northeast = northeast;
    }

    /**
     * 
     * @return
     *     The southwest
     */
    public Southwest_ getSouthwest() {
        return southwest;
    }

    /**
     * 
     * @param southwest
     *     The southwest
     */
    public void setSouthwest(Southwest_ southwest) {
        this.southwest = southwest;
    }

}
