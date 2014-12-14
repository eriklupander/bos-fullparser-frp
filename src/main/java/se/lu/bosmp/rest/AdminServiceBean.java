package se.lu.bosmp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.lu.bosmp.scanner.ReportFileScanner;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/rest/admin")
public class AdminServiceBean {

    @Autowired
    ReportFileScanner reportFileScanner;

    @RequestMapping(method = RequestMethod.POST, value = "/parse/test")
    public ResponseEntity<String> parseTest() {
        int scan = reportFileScanner.scan();
        return new ResponseEntity("Scan successful, " + scan + " items", HttpStatus.OK);
    }

}
