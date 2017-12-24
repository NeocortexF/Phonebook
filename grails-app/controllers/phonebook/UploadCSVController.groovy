package phonebook

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class UploadCSVController implements Validateable {

    MultipartFile featuredImageFile
    Long id
    Integer version

    static constraints = {
        id nullable: false
        version nullable: false
        featuredImageFile  validator: { val, obj ->
            if ( val == null ) {
                return false
            }
            if ( val.empty ) {
                return false
            }

            ['csv', 'txt'].any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }
}
