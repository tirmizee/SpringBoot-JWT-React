import React, { Component } from 'react';
import { Link, Redirect } from 'react-router-dom';
import AuthenManager from '../../commons/AuthenManager';
import ApiManager from '../../commons/APIManager';
import { Button, Form, Grid, Header, Image, Message, Segment } from 'semantic-ui-react'

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      loading : false,
      error: {
        isError: false,
        errorMessage: ''
      }
    }
    this.onChange = this.onChange.bind(this);
    this.onSubmitForm = this.onSubmitForm.bind(this);
    this.validate = this.validate.bind(this);
  }

  setSateError(error, callback) {
    return this.setState({
      ...this.state,
      loading : false,
      error: error
    }, callback);
  }

  onChange(e) {
    this.setState({
      [e.target.name]: e.target.value
    })
  }

  validate() {
    let isValid = true;
    return isValid;
  }

  onSubmitForm(e) {
    e.preventDefault();

    let callback = () => { 

      const { username, password } = this.state;
      let usernameAndPassword = username + ':' + password;
      let usernameAndPasswordBase64 = btoa(usernameAndPassword);
  
      
      AuthenManager.login(usernameAndPasswordBase64, (response) => {
  
        if (response.status == 200 && response.data.status) {
          AuthenManager.setAuthenticated(response.data.data.token);
          this.props.history.push('/');
       
        } else {
  
          const errorState = {
            isError: true,
            errorMessage: '[ER001] : username or password invalid!'
          }
  
          this.setSateError(errorState, () => {
            setTimeout(() => {
              this.setSateError({ isError: false })
            }, 7000);
          });
  
        }
      });
    }
    
    this.setState({ loading: true}, callback);

  }

  render() {

    const { loading } = this.state;

    return (
      <Grid textAlign='center' style={{ height: '100vh' }} verticalAlign='middle'>
        <Grid.Column style={{ maxWidth: 450 }}>
          <Header as='h2' color='teal' textAlign='center'>
            Log-in to your account
          </Header>
          <Form size='large' onSubmit={this.onSubmitForm}>
            <Segment padded='very'>
              <br />
              <Form.Input
                fluid icon='user'
                iconPosition='left'
                placeholder='E-mail address'
                name="username" value={this.state.username} onChange={this.onChange} />
              <Form.Input
                fluid
                icon='lock'
                iconPosition='left'
                placeholder='Password'
                type='password'
                name="password" value={this.state.password} onChange={this.onChange} />

              <Button color='teal' fluid size='large' type="submit" loading={loading}>
                Login
          </Button>
            </Segment>
          </Form>
          <Message>
            New to us? <a href='#'>Sign Up</a>
          </Message>
        </Grid.Column>
      </Grid>
    );
  }
}

export default Login;
