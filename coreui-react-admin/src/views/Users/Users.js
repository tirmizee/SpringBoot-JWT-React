import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Badge, Card, CardBody, CardHeader, Col, Collapse, FormGroup, Input, Row, Label, CardFooter, Button, InputGroup, InputGroupAddon, InputGroupText } from 'reactstrap';
import usersData from './UsersData';
import BootstrapTable from 'react-bootstrap-table-next';

const columns = [{
  dataField: 'id',
  text: 'Product ID',
  sort: true
}, {
  dataField: 'name',
  text: 'Product Name',
  sort: true
}, {
  dataField: 'price',
  text: 'Product Price',
  sort: true
}];

const defaultSorted = [{
  dataField: 'name',
  order: 'desc'
}];

const products = [
  { id: "1", name: "Pratya", price: 3000 },
  { id: "2", name: "Pratya", price: 3000 },
  { id: "3", name: "Pratya", price: 3000 },
  { id: "4", name: "Pratya", price: 3000 },
  { id: "5", name: "Pratya", price: 3000 }
];

class Users extends Component {

  constructor(props) {
    super(props);
    this.state = {
      collapse: true,
      search: {
        username: '',
        firstName: '',
        lastName: '',
        tel: ''
      }
    };

    this.toggle = this.toggle.bind(this);
    this.toggleFade = this.toggleFade.bind(this);
    this.onChange = this.onChange.bind(this);

  }

  onChange = (e) => {
    this.setState({
      search: { [e.target.name]: e.target.value }
    }, () => console.log(this.state.search));
  }

  toggle = () => {
    this.setState({ collapse: !this.state.collapse });
  }

  toggleFade = () => {
    this.setState((prevState) => { return { fadeIn: !prevState } });
  }

  render() {

    const { collapse } = this.state;

    return (
      <div className="animated fadeIn">
        <Row>
          <Col lg={12}>
            <Card>
              <CardHeader>
                <strong>Card actions</strong>
                <div className="card-header-actions">
                  <a className="card-header-action btn btn-minimize" data-target="#collapseExample" onClick={this.toggle}><i className={collapse ? "icon-arrow-down" : "icon-arrow-up"}></i></a>
                </div>
              </CardHeader>
              <Collapse isOpen={collapse} id="collapseExample">
                <CardBody>
                  <FormGroup row>
                    <Col md="2"></Col>
                    <Col md="4">
                      <FormGroup>
                        <Label htmlFor="Username"><strong>Username</strong></Label>
                        <InputGroup>
                          <InputGroupAddon addonType="prepend">
                            <InputGroupText>
                              <i className="fa fa-user"></i>
                            </InputGroupText>
                          </InputGroupAddon>
                          <Input type="text" name="username" onChange={this.onChange} placeholder="" />
                        </InputGroup>
                      </FormGroup>
                    </Col>
                    <Col md="4">
                      <FormGroup>
                        <Label htmlFor="tel"><strong>Tel</strong></Label>
                        <InputGroup>
                          <InputGroupAddon addonType="prepend">
                            <InputGroupText>
                              <i className="fa fa-phone"></i>
                            </InputGroupText>
                          </InputGroupAddon>
                          <Input type="text" name="tel" onChange={this.onChange} placeholder="" />
                        </InputGroup>
                      </FormGroup>
                    </Col>
                    <Col md="2"></Col>
                  </FormGroup>
                  <FormGroup row>
                    <Col md="2"></Col>
                    <Col md="4">
                      <FormGroup>
                        <Label htmlFor="firstName"><strong>First Name</strong></Label>
                        <InputGroup>
                          <InputGroupAddon addonType="prepend">
                            <InputGroupText>
                              <i className="fa fa-address-book-o"></i>
                            </InputGroupText>
                          </InputGroupAddon>
                          <Input type="text" name="firstName" onChange={this.onChange} placeholder="" />
                        </InputGroup>
                      </FormGroup>
                    </Col>
                    <Col md="4">
                      <FormGroup>
                        <Label htmlFor="lastName"><strong>Last Name</strong></Label>
                        <InputGroup>
                          <InputGroupAddon addonType="prepend">
                            <InputGroupText>
                              <i className="fa fa-address-book-o"></i>
                            </InputGroupText>
                          </InputGroupAddon>
                          <Input type="text" name="lastName" onChange={this.onChange} placeholder="" />
                        </InputGroup>
                      </FormGroup>
                    </Col>
                    <Col md="2"></Col>
                  </FormGroup>
                  <FormGroup>
                    <center>
                      <Button type="submit" size="sm" color="primary"><i className="fa fa-dot-circle-o"></i> Submit</Button>
                      {' '}
                      <Button type="reset" size="sm" color="danger"><i className="fa fa-ban"></i> Reset</Button>
                    </center>
                  </FormGroup>
                </CardBody>
              </Collapse>
            </Card>
          </Col>
        </Row>
        {/* Data Table */}
        <Row>
          <Col lg={12}>
            <Card>
              <CardHeader>
                <strong>Card actions</strong>
              </CardHeader>
              <CardBody>
                <BootstrapTable
                  bootstrap4
                  keyField="id"
                  data={products}
                  columns={columns}
                  defaultSorted={defaultSorted}
                />
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    )
  }
}

export default Users;
