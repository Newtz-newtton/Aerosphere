package com.aerosphere.common.constants;

/**
 * Class Name : ApplicationConstants
 *
 * Purpose :
 *     Stores application-wide constant values to eliminate hardcoded strings
 *     and improve maintainability.
 *
 * Responsibilities :
 *     - API version constants.
 *     - Common success and error messages.
 *     - Shared application values.
 *
 * Module :
 *     Common
 */
public final class ApplicationConstants {

    private ApplicationConstants() {

    }


    public static final String API_VERSION = "/api/v1";

    /* Success Messages */

    public static final String CREATED_SUCCESSFULLY = "Created successfully";

    public static final String UPDATED_SUCCESSFULLY = "Updated successfully";

    public static final String DELETED_SUCCESSFULLY = "Deleted successfully";

    public static final String FETCHED_SUCCESSFULLY = "Data fetched successfully";

}