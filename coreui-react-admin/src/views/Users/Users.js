import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Axios from 'axios';
import { ACCESS_TOKEN, API_LOGIN_URI, API_LOGOUT_URI } from '../../constants'
import { Badge, Card, CardBody, CardHeader, Col, Collapse, FormGroup, Input, Row, Label, CardFooter, Button, InputGroup, InputGroupAddon, InputGroupText, Modal, ModalBody, ModalFooter, ModalHeader } from 'reactstrap';
import {POST,GET} from '../../commons/APIManager';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import DataTable from 'react-data-table-component';
import {Segment, Loader } from 'semantic-ui-react'

import './index.css';

const sortIcon = <i className="cui-arrow-top icons font-2xl d-block"></i>;
const actions = (<div>
  <i className="fa fa-file fa-md"></i>&nbsp;
  <i className="fa fa-question-circle fa-md"></i>&nbsp;
  <i className="fa fa-plus fa-md"></i>&nbsp;
  <i className="fa fa-wrench fa-md"></i>
</div>);
const columns = ( handleClick, handleClickButton) => [
  {
    name: 'Order',
    selector: 'order',
    maxWidth: '120px'
  },
  {
    name: 'Username',
    selector: 'username',
    sortable: true,
    left: true,
    maxWidth: '200px',
    style: {
      color: '#202124',
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
    name: 'Status',
    selector: 'enabled',
    sortable: true,
    cell: (row) => row.enabled ? (<span className="badge badge-success enabled">Active</span>) : (<span className="badge badge-danger enabled">Inactive</span>)
  },
  {
    name: 'Social',
    sortable: true,
    cell: (row) => (<span>
      <i className="fa fa-facebook-square fa-lg icon-unsuccess"></i>{' '} 
      <i className="fa fa-github fa-lg"></i>{' '} 
      <i className="fa fa-google-plus-square fa-lg"></i>{' '} 
      <i className="fa fa-linkedin-square fa-lg"></i>
    </span>)
  },
  {
    name: 'Action',
    selector: 'action',
    button: true,
    width: '250px',
    cell: (row) => (<>
      <Button className="btn-behance btn-brand icon mr-1 mb-1 btn btn-secondary" onClick={(e) => handleClick(e, 'btnView', row)}><i className="fa fa-behance"></i></Button>
      <Button className="btn-spotify btn-brand icon mr-1 mb-1 btn btn-secondary" onClick={(e) => handleClick(e, 'btnEdit', row)}><i className="fa fa-spotify"></i></Button>
      <Button className="btn-pinterest btn-brand icon mr-1 mb-1 btn btn-secondary" onClick={(e) => handleClick(e, 'btnDelete', row)}><i className="fa fa-pinterest"></i></Button>
    </>),
  }
];

const styles = {
  headRow: {
    style: {
      borderTopStyle: 'solid',
      borderTopWidth: '1px',
      borderTopColor: '#000000'
    }
  },
  headCells: {
    style: {
      color: '#202124',
      fontSize: '14px',
    }
  },
  cells: {
    style: {}
  }
};

class Users extends Component {

  constructor(props) {
    super(props);
    this.state = {
      isOpenModal : false,
      collapse: true,
      loading: false,
      data: [],
      totalRows: 0,
      page: 1,
      perPage: 10,
      loadingApi : false,
      search: {
        username: '',
        firstName: '',
        lastName: '',
        tel: ''
      }
    };

    this.toggle = this.toggle.bind(this);
    this.toggleFade = this.toggleFade.bind(this);
    this.toggleModal = this.toggleModal.bind(this);
    this.handleSort = this.handleSort.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
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

  toggleModal = (e) => { 
    this.setState((prevState) => { return { isOpenModal: !prevState.isOpenModal } });
  }

  handleSubmit = (e) => {

    let { perPage, page, search = {}} = this.state;
    let data = { page: page - 1, size: perPage, search : search };

    POST('/user/all', data).then(response => {

      if(response.status === 200) {
        console.log(JSON.stringify(response.data));
        
        let users = response.data.content.map((o, i) => {
          return {
            order: (page - 1) * perPage + (i + 1),
            id: o.userId,
            email: o.email,
            username: o.username,
            firstName: o.firstName,
            enabled : o.enabled
          };
        });
  
        const state = {
          loading: false,
          data: users,
          perPage : perPage, 
          totalRows : response.totalElements
        }

        this.setState(state);
      }

    });

  }

  handleClickButton = (e, target, data) => {
    switch (target) {

      case 'btnView': {
        this.toggleModal();
        break;
      }

      case 'btnEdit': {
        this.props.history.push({ pathname: `/users/${data.id}`, state: { data: data } }); 
        break;
      }

      case 'btnDelete': break;
    }
  }

  handleChangeInput = (e) => {
    this.setState({
      search: { 
        ...this.state.search, 
        [e.target.name]: e.target.value 
      },
    }, () => console.log(this.state.search));
  }

  handleSort = (column, sortDirection) => {

    let sort = sortDirection == 'asc' ? 'A' : 'D';
    let { perPage, page, search = {}} = this.state;
    let data = { 
      page: page - 1, 
      size: perPage,
      sort: sort, 
      sortField: column.selector, 
      search : search 
    }

    POST('/user/all', data).then(response => {
      
      if(response.status === 200) {
        let users = response.data.content.map((o, i) => {
          return {
            order: (page - 1) * perPage + (i + 1),
            id: o.userId,
            email: o.email,
            username: o.username,
            firstName: o.firstName,
            enabled : o.enabled
          };
        });
        this.setState({
          loading: false,
          data: users,
          perPage : perPage, 
          totalRows : response.totalElements
        });
      }
        
    });
  };

  handlePageChange = async page => {

    const { perPage, search = {} } = this.state;
    let data = { page: page - 1, size: perPage, search : search };

    POST('/user/all', data).then(response => {

      if(response.status === 200){
        let users = response.data.content.map((o, i) => {
          return {
            order: (page - 1) * perPage + (i + 1),
            id: o.userId,
            email: o.email,
            username: o.username,
            firstName: o.firstName,
            enabled : o.enabled
          };
        });
        this.setState({
          loading: false,
          data: users,
          // totalRows : response.totalElements
        });
      }
    });

  }

  handlePerRowsChange = async (perPage, page) => {

    POST('/user/all', { page: page - 1, size: perPage, search : {}}).then(response => {

      if(response.status === 200){
        let users = response.data.content.map((o, i) => {
          return {
            order: (page - 1) * perPage + (i + 1),
            id: o.userId,
            email: o.email,
            username: o.username,
            firstName: o.firstName,
            enabled : o.enabled
          };
        });
  
        this.setState({
          loading: false,
          data: users,
          perPage,
        });
      }
    });

  }

  componentDidMount = () => {

    const callback = () => { 
      const { page, perPage, search = {}} = this.state;
      const data = { page: page - 1, size: perPage, search:search };
      POST('http://localhost:8888/jwt/user/all', data).then(response => {
  
        if(response.status === 200){
          let users = response.data.content.map((o, i) => {
            return {
              order: (page - 1) * perPage + (i + 1),
              id: o.userId,
              email: o.email,
              username: o.username,
              firstName: o.firstName,
              enabled : o.enabled
            };
          });
    
          this.setState({
            data: users,
            totalRows: response.data.totalElements,
            loading: false,
            loadingApi: false
          });
        }
      });
    }

    this.setState({loadingApi: true}, callback);

  }

  render() {

    const { collapse, loading, data, totalRows, search, isOpenModal,loadingApi } = this.state;

    return (
      <div className="animated fadeIn">

        <Modal 
          isOpen={isOpenModal} 
          toggle={this.toggleModal} 
          className={this.props.className}
          modalTransition={{ timeout: 50 }}
          backdropTransition={{ timeout: 50 }}
        >
          <ModalHeader toggle={this.toggleModal}>Modal title</ModalHeader>
          <ModalBody>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore
            et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
            culpa qui officia deserunt mollit anim id est laborum.
          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={this.toggleModal}>Do Something</Button>{' '}
            <Button color="secondary" onClick={this.toggleModal}>Cancel</Button>
          </ModalFooter>
        </Modal>

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
                          <Input type="text" name="username" value={search.username} onChange={this.handleChangeInput} placeholder="" />
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
                          <Input type="text" name="tel" value={search.tel} onChange={this.handleChangeInput} placeholder="" />
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
                          <Input type="text" name="firstName" value={search.firstName} onChange={this.handleChangeInput} placeholder="" />
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
                          <Input type="text" name="lastName" value={search.lastName} onChange={this.handleChangeInput} placeholder="" />
                        </InputGroup>
                      </FormGroup>
                    </Col>
                    <Col md="2"></Col>
                  </FormGroup>
                  <FormGroup>
                    <center>
                      <Button className="btn-twitter btn-brand text mr-1 mb-1 btn btn-secondary" onClick={this.handleSubmit}><span>Sreach</span></Button>
                      <Button className="btn-youtube btn-brand text mr-1 mb-1 btn btn-secondary"><span>Clear</span></Button>
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
                      <Segment basic loading={loadingApi}>
                        <DataTable
                          data={data}
                          columns={columns(this.handleClickButton, this.handleClickButton)}
                          progressPending={loading}
                          actions={actions}
                          sortIcon={sortIcon}
                          onSort={this.handleSort}
                          customStyles={styles}
                          onChangeRowsPerPage={this.handlePerRowsChange}
                          onChangePage={this.handlePageChange}
                          pagination
                          paginationServer
                          paginationTotalRows={totalRows}
                          highlightOnHover
                          pointerOnHover
                        />
                      </Segment>
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
