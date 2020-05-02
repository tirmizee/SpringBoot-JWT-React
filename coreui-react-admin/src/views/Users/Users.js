import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Axios from 'axios';
import { ACCESS_TOKEN, API_LOGIN_URI, API_LOGOUT_URI } from '../../constants'
import { Badge, Card, CardBody, CardHeader, Col, Collapse, FormGroup, Input, Row, Label, CardFooter, Button, InputGroup, InputGroupAddon, InputGroupText } from 'reactstrap';
import usersData from './UsersData';
import ApiManager from '../../commons/APIManager';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import DataTable from 'react-data-table-component';

const columns = handleClick => [
  {
    name: 'Order',
    selector: 'order',
    maxWidth: '120px'
  },
  {
    name: 'Username',
    selector: 'username',
    sortable: true,
    maxWidth: '200px',
    style: {
      color: '#202124',
      fontSize: '14px',
      fontWeight: 500,
    }
  },
  {
    name: 'Email',
    selector: 'email',
    sortable: true,
    maxWidth: '300px',
    style: {
      color: '#00617e',
      fontSize: '14px',
      fontWeight: 500,
    }
  },
  {
    name: 'First Name',
    selector: 'firstName',
    sortable: true,
  },
  {
    name: 'Action',
    selector: 'action',
    button: true,
    width : '250px',
    cell: (row) => (<>
      <Button className="btn-behance btn-brand icon mr-1 mb-1 btn btn-secondary"  onClick={(e) => handleClick(e, 'btnView', row)}><i className="fa fa-behance"></i></Button>
      <Button className="btn-spotify btn-brand icon mr-1 mb-1 btn btn-secondary"  onClick={(e) => handleClick(e, 'btnEdit', row)}><i className="fa fa-spotify"></i></Button>
      <Button className="btn-pinterest btn-brand icon mr-1 mb-1 btn btn-secondary" onClick={(e) => handleClick(e, 'btnDelete', row)}><i className="fa fa-pinterest"></i></Button>
    </>),
  }
];

const styles = {
  headRow: {
    style: {
      border: 'none',
    }
  },
  headCells: {
    style: {
      color: '#202124',
      fontSize: '14px',
    }
  }
};

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
    this.handleSort = this.handleSort.bind(this);
    this.handleClickButton = this.handleClickButton.bind(this);
    this.handlePageChange = this.handlePageChange.bind(this);
    this.handleChangeInput = this.handleChangeInput.bind(this);
    this.handlePerRowsChange = this.handlePerRowsChange.bind(this);

  }
 
  toggle = () => {
    this.setState({ collapse: !this.state.collapse });
  }

  toggleFade = () => {
    this.setState((prevState) => { return { fadeIn: !prevState } });
  }

  handleClickButton = (e, target, data) => {
    switch (target) {
      case 'btnView': break;
      case 'btnEdit': this.props.history.push({ pathname : `/users/${data.username}`, state :{ data: data}}); break;
      case 'btnDelete': break;
    }
  }

  handleChangeInput = (e) => {
    this.setState({
      search: { [e.target.name]: e.target.value }
    }, () => console.log(this.state.search));
  }


  handleSort = (column, sortDirection) => {

    let sort = sortDirection == 'asc' ? 'A' : 'D';
    let headers = { 'Authorization': `Bearer ${localStorage.getItem(ACCESS_TOKEN)}` };
    let { perPage, page } = this.state;

    Axios
      .post('http://localhost:8888/jwt/user/all', {page: page - 1, size: perPage, sort : sort, sortField :column.selector}, { headers })
      .then(res => {

        let users = res.data.content.map((o, i) => {
          return {
            order : (page - 1) * perPage + (i + 1),
            id: o.userId,
            email: o.email,
            username: o.username,
            firstName: o.firstName
          };
        });

        this.setState({
          loading: false,
          data: users,
        });

      })


  };

  handlePageChange = async page => {

    const { perPage } = this.state;

    let callback = (response) => {

      let users = response.data.content.map((o, i) => {
        return {
          order : (page - 1) * perPage + (i + 1),
          id: o.userId,
          email: o.email,
          username: o.username,
          firstName: o.firstName
        };
      });

      this.setState({
        loading: false,
        data: users,
      });
    
    }

    ApiManager.POST('http://localhost:8888/jwt/user/all', {page: page - 1, size: perPage}, callback);

  }

  handlePerRowsChange = async (perPage, page) => {

    console.log('handlePerRowsChange : ' + page);

    let callback = (response) => {

      let users = response.data.content.map((o, i) => {
        return {
          order : (page - 1) * perPage + (i + 1),
          id: o.userId,
          email: o.email,
          username: o.username,
          firstName: o.firstName
        };
      });

      this.setState({
        loading: false,
        data: users,
        perPage,
      });
    
    }

    ApiManager.POST('http://localhost:8888/jwt/user/all', {page : page - 1, size : perPage}, callback);

  }

  componentDidMount = () => {

    const { page, perPage } = this.state;

    let callback = (response) => {

      let users = response.data.content.map((o, i) => {
        return {
          order : (page - 1) * perPage + (i + 1),
          id: o.userId,
          email: o.email,
          username: o.username,
          firstName: o.firstName
        };
      });

      this.setState({
        data: users,
        totalRows: response.data.totalElements,
        loading: false
      });
    
    }

    ApiManager.POST('http://localhost:8888/jwt/user/all', {page : page - 1, size : perPage}, callback);

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
                          <Input type="text" name="username" onChange={this.handleChangeInput} placeholder="" />
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
                          <Input type="text" name="tel" onChange={this.handleChangeInput} placeholder="" />
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
                          <Input type="text" name="firstName" onChange={this.handleChangeInput} placeholder="" />
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
                          <Input type="text" name="lastName" onChange={this.handleChangeInput} placeholder="" />
                        </InputGroup>
                      </FormGroup>
                    </Col>
                    <Col md="2"></Col>
                  </FormGroup>
                  <FormGroup>
                    <center>
                      <button class="btn-twitter btn-brand text mr-1 mb-1 btn btn-secondary"><span>Sreach</span></button>
                      <button class="btn-youtube btn-brand text mr-1 mb-1 btn btn-secondary"><span>Clear</span></button>
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
                      columns={columns(this.handleClickButton)}
                      data={data}
                      progressPending={loading}
                      pagination
                      paginationServer
                      highlightOnHover
                      pointerOnHover
                      onSort={this.handleSort}
                      customStyles={styles}
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
