package architecture.template.vmm.service;

import architecture.template.vmm.model.request.AuthRequest;
import architecture.template.vmm.model.response.AuthResponse;

public interface SessionService {

  AuthResponse loggin(AuthRequest authRequest);

}
