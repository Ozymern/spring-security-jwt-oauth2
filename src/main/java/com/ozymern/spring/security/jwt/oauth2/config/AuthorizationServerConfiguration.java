package com.ozymern.spring.security.jwt.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
//El servidor de autorización valida clienty las usercredenciales y proporciona los tokens
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	 private String clientid = "client-id";
	   private String clientSecret = "my-secret-key";
	   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
	   		"MIIEpQIBAAKCAQEAufppk5G/OSAuBXs2bYgr+9ruCx6ULh+ayXzkU30NQaFLprTu\r\n" + 
	   		"7XllG8eROLmt2oN+KU4LlnBZrQK8gsnuzfybAtBaDPEAXfuVDQjCl7rbnYRTfc3C\r\n" + 
	   		"yS9lBpd/AM/snfTcx/4E7Dc4rhPTjk9sb0DQvQ2KErf0qDW50+LuWEhvhNNk/Tea\r\n" + 
	   		"N+jadts2pn5WuQ+vdDsExsSk2FGbKBWY9IDzgoklMLHE1wlisI6E7L5TCzxgOSTi\r\n" + 
	   		"/ncU9+tQ/H7jXPnEXzBQBZAz8tvMDSxKSqHO/RmvEMiSTSllulY4BgQ8RPdTEkyr\r\n" + 
	   		"ZXpQU2HStqCWzy8YQuqwqa3TmESlkmycUypjNwIDAQABAoIBACCUjtRMTxyZ15Yc\r\n" + 
	   		"PK+5kEMB2nvWMo1V+g7cMz+xA5aAxDkDL2wovR69umIenSdPq9Vel8cWDY+ykAW1\r\n" + 
	   		"jIFHhHxctbnYqoDMZxMuerCMCpcwrFO4rSwDgoZCGfyxcDlHwTnD9WbQUbylOoWO\r\n" + 
	   		"wmdHCopkCdE3qsOhDdnhwnFy1x6EgRj38/o992B7xQt2qtzcsG1B0aB0mQgUHw+I\r\n" + 
	   		"JON/gpPH6pGLFFMW5ZiYEH3JjM/RAroXwc3g+XmunyoTkyHTkffFgORd8kChea80\r\n" + 
	   		"XyH16ONsUzlh1/dUQa//OhrF/Mx0+eVQoe6sVaLzPv8/AGZuf77JSiWqMRLyR6/D\r\n" + 
	   		"0cVh9aECgYEA9BrMJKUvfQieOTuN+YADHhQwY1QiFcIzqnUX2huTrkbCluQ+CMR6\r\n" + 
	   		"pIdjs6JCrQfi7Mcr7UA7jydNaiIZYk/Z6h7N9ljKXy0F0UNmsnnfWxWdFgmNKkv4\r\n" + 
	   		"DYePWaAxFND1gJBI1ALwhT5NgP72sZa89CYeXw/gG7yUaj0nQktTXycCgYEAwwp8\r\n" + 
	   		"1JRO+AK3JbRZciu/Unl6TNgbW2hTQD4rPuk9aB94A/nHsEA9m8RMzB8GGpmBkyDA\r\n" + 
	   		"4ix/5uJuNdxR5lc/p1aVCl1lg3ZlKyMBi0bY6O//6rYW1IvKVCgkigXxT2aWw1x5\r\n" + 
	   		"5vbVmT2Y0DlCCmGQqaWpImt70xSg/QKOsv51ZXECgYEAlmrs4KfpW2K0HRBVrCj1\r\n" + 
	   		"wtMt/gcZ+c5xh9gfmobjucBgqBp9H2nzKO9EwKx76aOycbVJnCi4ir3tNjlM6wfJ\r\n" + 
	   		"gDr7L5IHIh7cNtcRsWjn+XiYE7xPAIFPmQavjsrnc6xPm6lNN1eYXoLFv3ddDJGW\r\n" + 
	   		"XdwfWvStZGoQCmckifVgqoECgYEAhQg5PtHtzxIjE8+gltQqCjobZiCdAEr3RcFh\r\n" + 
	   		"w+ZY4RVdyfBr+NbIbt+EtlGO20PK8qyLg3lwv+2/2HLvVdo664uN2uT7YaC2vqaA\r\n" + 
	   		"3huzxaY1e0U56z1yAtbinVYSUuMZgCUqbJP9+yjde2Ein2wOvPfmljiT29SdPhWp\r\n" + 
	   		"eYNAVbECgYEArXKD27W+ZyjRlZTXXxHgqZxF5CgEHaP9dVC12WsQ1MJ3ak0R9czX\r\n" + 
	   		"UnSuPh9DUJUhxDigoxDsq5UZGHPFmGajRvzaanxjsWyQfTE6RT+5hLOZ9syjPGED\r\n" + 
	   		"n274H8j2K3X/upTMEmGttYe5uwRkkt52s+MCLtaRlAZJerJk4eoeAJY=\r\n" + 
	   		"-----END RSA PRIVATE KEY-----";
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" + 
	   		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAufppk5G/OSAuBXs2bYgr\r\n" + 
	   		"+9ruCx6ULh+ayXzkU30NQaFLprTu7XllG8eROLmt2oN+KU4LlnBZrQK8gsnuzfyb\r\n" + 
	   		"AtBaDPEAXfuVDQjCl7rbnYRTfc3CyS9lBpd/AM/snfTcx/4E7Dc4rhPTjk9sb0DQ\r\n" + 
	   		"vQ2KErf0qDW50+LuWEhvhNNk/TeaN+jadts2pn5WuQ+vdDsExsSk2FGbKBWY9IDz\r\n" + 
	   		"goklMLHE1wlisI6E7L5TCzxgOSTi/ncU9+tQ/H7jXPnEXzBQBZAz8tvMDSxKSqHO\r\n" + 
	   		"/RmvEMiSTSllulY4BgQ8RPdTEkyrZXpQU2HStqCWzy8YQuqwqa3TmESlkmycUypj\r\n" + 
	   		"NwIDAQAB\r\n" + 
	   		"-----END PUBLIC KEY-----\r\n" + 
	   		"";

	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Bean
	   //El JwtAccessTokenConverterutiliza el certificado autofirmado para firmar las fichas generadas. 
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }
} 