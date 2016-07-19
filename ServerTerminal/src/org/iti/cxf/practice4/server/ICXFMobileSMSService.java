package org.iti.cxf.practice4.server;

import java.io.Serializable;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.iti.cxf.practice4.dto.BatchsubmitDTO;
import org.iti.cxf.practice4.dto.SingleSubmitListDTO;

@WebService
public interface ICXFMobileSMSService extends Serializable {

	@WebMethod
	@WebResult
	public String singleShotSMS(@WebParam String phone, @WebParam String content);

	@WebMethod
	@WebResult
	public String singleShotSMSCollection(
			@WebParam List<SingleSubmitListDTO> list);

	@WebMethod
	@WebResult
	public String batchSubmit(@WebParam BatchsubmitDTO batchsubmitDTO);

	@WebMethod
	@WebResult String sayHello(@WebParam String name);
}
