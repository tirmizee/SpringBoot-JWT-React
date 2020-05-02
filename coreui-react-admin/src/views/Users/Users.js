import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Axios from 'axios';
import { ACCESS_TOKEN, API_LOGIN_URI, API_LOGOUT_URI } from '../../constants'
import { Badge, Card, CardBody, CardHeader, Col, Collapse, FormGroup, Input, Row, Label, CardFooter, Button, InputGroup, InputGroupAddon, InputGroupText } from 'reactstrap';
import usersData from './UsersData';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import DataTable from 'react-data-table-component';

const columns = [
  {
    name: 'Order',
    selector: 'order'
  },
  {
    name: 'Username',
    selector: 'username',
    sortable: true,
  },
  {
    name: 'Email',
    selector: 'email',
    sortable: true,
  },
  {
    name: 'First Name',
    selector: 'firstname',
    sortable: true,
  }
  
];

class Users extends Component {

  constructor(props) {
    super(props);
    this.state = {
      collapse: true,
      loading: false,
      data: [],
      totalRows: 0,
      page : 1,
      perPage: 10,
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
    this.handlePageChange = this.handlePageChange.bind(this);
    this.handlePerRowsChange = this.handlePerRowsChange.bind(this);

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

  handlePageChange = async page => {

    console.log('handlePageChange : ' + page);

    const headers = { 'Authorization': `Bearer ${localStorage.getItem(ACCESS_TOKEN)}` };

    const { perPage } = this.state;

    Axios
      .post('http://localhost:8888/jwt/user/all', {page: page - 1, size: perPage}, { headers })
      .then(res => {

        let users = res.data.content.map((o, i) => {
          return {
            order : (page - 1) * perPage + (i + 1),
            id: o.userId,
            email: o.email,
            username: o.username,
            firstname: o.firstName
          };
        });

        this.setState({
          loading: false,
          data: users,
        });

      })
   
  }

  handlePerRowsChange = async (perPage, page) => {

    console.log('handlePerRowsChange : ' + page);

    const headers = { 'Authorization': `Bearer ${localStorage.getItem(ACCESS_TOKEN)}` };

    Axios
      .post('http://localhost:8888/jwt/user/all', {page : page - 1, size : perPage}, { headers })
      .then(res => {

        let users = res.data.content.map((o, i) => {
          return {
            order : (page - 1) * perPage + (i + 1),
            id: o.userId,
            email: o.email,
            username: o.username,
            firstname: o.firstName
          };
        });

        this.setState({
          loading: false,
          data: users,
          perPage,
        });

      })

  }

  componentDidMount = () => {

    const { page, perPage } = this.state;

    const headers = { 'Authorization': `Bearer ${localStorage.getItem(ACCESS_TOKEN)}` };

    Axios
      .post('http://localhost:8888/jwt/user/all', {page : page - 1, size : perPage}, { headers })
      .then(res => {
        let users = res.data.content.map((o, i) => {
          return {
            order : (page - 1) * perPage + (i + 1),
            id: o.userId,
            email: o.email,
            username: o.username,
            firstname: o.firstName
          };
        });

        this.setState({
          data: users,
          totalRows: res.data.totalElements,
          loading: false
        });

      }).catch(error => {});
  }

  render() {

    const { collapse, loading, data, totalRows } = this.state;

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
                <Row>
                  <Col md="12">
                    <DataTable
                      columns={columns}
                      data={data}
                      progressPending={loading}
                      pagination
                      paginationServer
                      highlightOnHover
                      paginationTotalRows={totalRows}
                      onChangeRowsPerPage={this.handlePerRowsChange}
                      onChangePage={this.handlePageChange}
                    />
                  </Col>
                </Row>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    )
  }
}

export default Users;
