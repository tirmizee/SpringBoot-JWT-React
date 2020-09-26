import React from 'react';
import { connect } from 'react-redux';
import { Button, Modal, ModalBody, ModalFooter, ModalHeader } from 'reactstrap';
import {saveTokenExpired} from '../../store/action/action'

class ModalRefreshToken extends React.Component { 

    render() {

        const {isTokenExpired} = this.props;

        return (
            <Modal 
                isOpen={isTokenExpired} 
                modalTransition={{ timeout: 50 }}
                backdropTransition={{ timeout: 50 }}
            >
                <ModalHeader>Session Timeout</ModalHeader>
                <ModalBody>
                    Do you want to continue ?.
                </ModalBody>
                <ModalFooter>
                    <Button color="primary" onClick={()=> {this.props.storeTokenExpired(false)}}>Yes</Button>{' '}
                    <Button color="secondary" onClick={()=> {window.location = '/login'}}>No</Button>
                </ModalFooter>
            </Modal>
        )
    }
    
};

const mapStateToProps = state => {
    return {
        isTokenExpired: state.token.isTokenExpired
    }
};

// const mapDispatchToProps = dispatch => bindActionCreators({
//     storeTokenExpired
// }, dispatch);

const mapDispatchToProps = dispatch => {
    return {
        storeTokenExpired : (isExpired) => { dispatch(saveTokenExpired(isExpired))}
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(ModalRefreshToken);