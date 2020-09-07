import React, { Component } from 'react';
import { Card, CardBody, CardHeader, Col, Row, Input, Collapse, Form, FormGroup, Label, Button } from 'reactstrap';

class User extends Component {

  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.toggleFade = this.toggleFade.bind(this);
    this.state = {
      collapse: true,
      fadeIn: true,
      timeout: 300
    };
  }

  toggle() {
    this.setState({ collapse: !this.state.collapse });
  }

  toggleFade() {
    this.setState((prevState) => { return { fadeIn: !prevState } });
  }

  render() {

    const { data : user } =  this.props.location.state;
    const { collapse } =  this.state;

    return (
      <div className="animated fadeIn">
        <Row>
          <Col lg={12}>
            <Card>
              <CardHeader>
                <strong>Card actions</strong>
              </CardHeader>
              <Collapse isOpen={collapse} id="collapseExample">
                <CardBody>

                  <Form action="" method="post" encType="multipart/form-data" className="form-horizontal">
                    <Row>
                      <Col md={3}></Col>
                      <Col md={3} xs={6} sm={12}>
                        <FormGroup>
                          <Label htmlFor="nf-email">Username</Label>
                          <Input type="text" value={user.username} name="nf-email" placeholder="" autoComplete="email" />
                        </FormGroup>
                      </Col>
                      <Col md={3} xs={6} sm={12}>
                        <FormGroup>
                          <Label htmlFor="nf-password">Email</Label>
                          <Input type="text" value={user.email} name="nf-password" placeholder="" autoComplete="current-password" />
                        </FormGroup>
                      </Col>
                      <Col md={3}></Col>
                    </Row>
                    <Row>
                      <Col md={3}></Col>
                      <Col md={3} xs={6} sm={12}>
                        <FormGroup>
                          <Label htmlFor="nf-email">First Name</Label>
                          <Input type="text" value={user.firstName} name="nf-email" placeholder="" autoComplete="email" />
                        </FormGroup>
                      </Col>
                      <Col md={3} xs={6} sm={12}>
                        <FormGroup>
                          <Label htmlFor="nf-password">Last Name</Label>
                          <Input type="text" value={user.lastName} name="nf-password" placeholder="" autoComplete="current-password" />
                        </FormGroup>
                      </Col>
                      <Col md={3}></Col>
                    </Row>
                    <Row>
                      <Col md={3}></Col>
                      <Col md={3} xs={6} sm={12}>
                        <FormGroup>
                          <Label htmlFor="nf-email">Citizent ID</Label>
                          <Input type="text" id="nf-email" name="nf-email" placeholder="" autoComplete="email" />
                        </FormGroup>
                      </Col>
                      <Col md={3} xs={6} sm={12}>
                        <FormGroup>
                          <Label htmlFor="nf-password">Tel</Label>
                          <Input type="text" id="nf-password" name="nf-password" placeholder="" autoComplete="current-password" />
                        </FormGroup>
                      </Col>
                      <Col md={3}></Col>
                    </Row>
                    <FormGroup>
                      <center>
                        <Button className="mr-1 mb-1 btn btn-info active"><span>Spotify</span></Button>
                        <Button className="btn-brand text mr-1 mb-1 btn btn-light active"><span>Clear</span></Button>
                      </center>
                    </FormGroup>
                  </Form>
                </CardBody>
              </Collapse>
            </Card>
          </Col>
        </Row>
      </div>
    )
  }
}

export default User;
