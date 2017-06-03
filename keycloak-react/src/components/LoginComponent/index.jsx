import Keycloak from "./keycloak";
import config from "./keycloak.json";
import React from "react";
import $ from "jquery";

var LoginComponent = React.createClass({
  getInitialState: function () {
    var that = this;

    let states = {profile: {}, contacts: {}};
    var keycloakInitConfig = {};
    keycloakInitConfig.url = config['auth-server-url'];
    keycloakInitConfig.realm = config['realm'];
    keycloakInitConfig.clientId = config['resource'];
    keycloakInitConfig.secret = (config['credentials'] || {})['secret'];

    let _keycloak = states.keycloak = Keycloak(keycloakInitConfig);

    _keycloak.init({
      onLoad: 'login-required'
    })
      .success((authenticated) => {
        if (authenticated) {
          var isExpired = _keycloak.isTokenExpired();
          var token = _keycloak.token;

          if (isExpired) {
            _keycloak.updateToken(5)
              .success(function () {
                // $.ajaxSetup({
                //   headers: {"common": {Authorization: "BEARER " + that.state.keycloak.token}}
                // });
              })
              .error(function () {
                console.error('Failed to refresh token');
              });
          }
          // $.ajaxSetup({
          //   headers: {"common": {Authorization: "BEARER " + that.state.keycloak.token}}
          // });

          states.keycloak.loadUserProfile().success(function (profile) {
            var newState = Object.assign({}, that.state);
            newState.profile = profile;
            that.setState(newState);
          });
        }
        else {
          window.location.reload();
        }
      })
      .error(function () {
        window.location.reload();
      });
    return states;
  },
  logout: function () {
    this.state.keycloak.logout();
  },
  getContracts: function () {
    var that = this;
    $.ajax({
      type: "GET",
      url: "http://localhost:7002/api/contracts",
      crossDomain: true,
      xhrFields: {cors: false},
      headers: {
        "Access-Control-Request-Headers": "x-requested-with, x-requested-by",
        "Authorization": "BEARER " + that.state.keycloak.token
      },
      error: function (a, b, c) {
        console.log(a);
      },
      success: function (data) {
        var newState = Object.assign({}, that.state);
        newState.contacts = data;
        that.setState(newState);
      }
    });
  },
  render: function () {
    return (
      <div>
        <div>
          <h5>You're logged in as:</h5>

          Id: {this.state.profile.id}
          <p/>
          Username: {this.state.profile.username}
          <p/>
          Email: {this.state.profile.email}
          <p/>
          Full Name: {this.state.profile.firstName} {this.state.profile.lastName}
          <p/>

          <button name="Logout" onClick={this.logout}>Logout</button>

          <button onClick={this.getContracts}>Contacts</button>
          <ul>
            {JSON.stringify(this.state.contacts)}
          </ul>
        </div>
      </div>
    );
  }
});

export default LoginComponent;
