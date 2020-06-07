import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
//import { Test } from './Products.styles';

class Products extends PureComponent { 
  constructor(props) {
    super(props);

    this.state = {
      hasError: false,
    };
  }

  componentWillMount = () => {
    console.log('Products will mount');
  }

  componentDidMount = () => {
    console.log('Products mounted');
  }

  componentWillReceiveProps = (nextProps) => {
    console.log('Products will receive props', nextProps);
  }

  componentWillUpdate = (nextProps, nextState) => {
    console.log('Products will update', nextProps, nextState);
  }

  componentDidUpdate = () => {
    console.log('Products did update');
  }

  componentWillUnmount = () => {
    console.log('Products will unmount');
  }

  render () {
    if (this.state.hasError) {
      return <h1>Something went wrong.</h1>;
    }
    return (
      <div className="ProductsWrapper">
        Test content
      </div>
    );
  }
}

Products.propTypes = {
  // bla: PropTypes.string,
};

Products.defaultProps = {
  // bla: 'test',
};

export default Products;
