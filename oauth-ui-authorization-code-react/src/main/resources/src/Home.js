import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import { withCookies } from 'react-cookie';

class Home extends Component {
  state = {
    isLoading: true,
    isAuthenticated: false,
    user: undefined,
    clientId: 'fooClientIdPassword',
    redirectUri: 'http://localhost:3000/'
  };

  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state.access_token = cookies.get('access_token');
    this.login = this.login.bind(this);
    this.logout = this.logout.bind(this);
  }

  async componentDidMount() {
    let i = window.location.href.indexOf('code');
    if(!this.isLoggedIn && i != -1){
        await this.retrieveToken(window.location.href.substring(i + 5));
    }

    if((this.state.isAuthenticated && this.isLoggedIn) || this.state.access_token != undefined){
      const { cookies } = this.props;
      const response = await fetch('/api/user', {method: 'GET', credentials: 'include',
      headers: {'Authorization': 'Bearer '+ cookies.get('access_token')}});
      const body = await response.text();
      console.log("body",body);  
      this.setState({user: JSON.parse(body)});
      this.setState({isAuthenticated: true});
      this.isLoggedIn = true;
    }
  }


  async retrieveToken(code){
    const response = await fetch('http://localhost:8081/spring-security-oauth-server/oauth/token?'+ 'grant_type=authorization_code&' + 'client_id='+ this.state.clientId + '&redirect_uri=' + this.state.redirectUri + '&code=' + code, 
    {
      method: 'POST',
      headers: {'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic '+btoa(this.state.clientId+":secret")}
    });
    const body = await response.text();
    console.log('response.text',body);
    this.saveToken(JSON.parse(body));
    
  } 

  saveToken(token){
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    const { cookies } = this.props;
    cookies.set("access_token", token.access_token, expireDate);
    console.log('Obtained Access token',token.access_token);
    this.state.access_token = token.access_token;
    this.setState({isAuthenticated: true});
    this.isLoggedIn = true;
    window.location.href = 'http://localhost:3000';
  }

  checkCredentials(){
    const { cookies } = this.props;
    console.log("access_token", cookies.get('access_token'));
    if(cookies.get('access_token') != '' && cookies.get('access_token') != undefined){
      return true;
    }
    return false;
  } 

  login() {
    let port = (window.location.port ? ':' + window.location.port : '');
    if (port === ':3000') {
      port = ':8082';
    }
    
    window.location.href = 'http://localhost:8081/spring-security-oauth-server/oauth/authorize?response_type=code&client_id=' + this.state.clientId + '&redirect_uri='+ this.state.redirectUri;
  }

  logout() {
    const { cookies } = this.props;
    cookies.remove('access_token');
    this.setState({isAuthenticated: false,access_token: undefined});
    this.isLoggedIn = false;
    window.location.reload();

    /*fetch('/api/logout', {method: 'POST', credentials: 'include',
      headers: {'Authorization': 'Bearer '+ cookies.get('access_token')}}).then(res => res.json())
      .then(response => {
        window.location.href = response.logoutUrl + "?id_token_hint=" +
          response.idToken + "&post_logout_redirect_uri=" + window.location.origin;
      });*/
  }

  render() {
    const message = this.state.user ?
      <h2>Welcome, {this.state.user.name}!</h2> :
      <p>Please log in to manage your JUG Tour.</p>;

    const button = this.state.isAuthenticated ?
      <div>
        <Button color="link"><Link to="/groups">Manage JUG Tour</Link></Button>
        <br/>
        <Button color="link" onClick={this.logout}>Logout</Button>
      </div> :
      <Button color="primary" onClick={this.login}>Login</Button>;

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          {message}
          {button}
        </Container>
      </div>
    );
  }
}

export default withCookies(Home);