package architecture.template.vmm.service.system;

import architecture.template.vmm.dao.user.UserDao;
import architecture.template.vmm.dto.TokenDto;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.helpers.JwtHelper;

import static architecture.template.vmm.messages.ExceptionsConstants.ERROR_TO_AUTH_USER;

import architecture.template.vmm.model.request.AuthRequest;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@Transactional
public class AuthService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserDao userDao;
  private final JwtHelper jwtHelper;
  private final PasswordEncoder passwordEncoder;
  //endregion

  //region Ctor
  public AuthService(
    final UserDao userDao,
    final JwtHelper jwtHelper,
    final PasswordEncoder passwordEncoder
  ) {
    this.userDao = userDao;
    this.jwtHelper = jwtHelper;
    this.passwordEncoder = passwordEncoder;
  }
  //endregion

  //region Implementations
  public TokenDto validateToken(TokenDto token) {
    log.info("Validating token");
    if (this.jwtHelper.validateToken(token.getAccessToken())) {
      return new TokenDto(token.getAccessToken());
    }
    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_TO_AUTH_USER);
  }

  public TokenDto login(AuthRequest user) {
    var usr = userDao.validateByUserInfo(user.getUsername());
    validPassword(user, usr);
    return new TokenDto(this.jwtHelper.createToken(usr.getUsername()));
  }
  //endregion

  //region Functions
  private void validPassword(AuthRequest userDto, UserEntity userEntity) {
    if (!this.passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_TO_AUTH_USER);
    }
  }
  //endregion

}
