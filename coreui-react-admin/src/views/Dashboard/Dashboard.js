import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';
import { getStyle, hexToRgba } from '@coreui/coreui/dist/js/coreui-utilities';
import React, { Component, lazy, Suspense } from 'react';
import { Line } from 'react-chartjs-2';
import { Badge, Button, ButtonGroup, ButtonToolbar, Card, CardBody, CardFooter, CardHeader, CardTitle, Col, Progress, Row, Table } from 'reactstrap';
import { Area, AreaChart, Bar, CartesianGrid, ComposedChart, Legend, ResponsiveContainer, Tooltip, XAxis, YAxis, Line as Line2 } from 'recharts';
import Widget01 from './Widget01';
import Widget02 from './Widget02';
import Widget04 from './Widget04';

const Widget03 = lazy(() => import('../../views/Widgets/Widget03'));

const brandPrimary = getStyle('--primary')
const brandSuccess = getStyle('--success')
const brandInfo = getStyle('--info')
const brandWarning = getStyle('--warning')
const brandDanger = getStyle('--danger')

const data = [
  {
    name: 'Page A', uv: 590, pv: 80, amt: 1400,
  },
  {
    name: 'Page B', uv: 868, pv: 96, amt: 1506,
  },
  {
    name: 'Page C', uv: 1397, pv: 100, amt: 989,
  },
  {
    name: 'Page D', uv: 1480, pv: 55, amt: 1228,
  },
  {
    name: 'Page E', uv: 1520, pv: 33, amt: 1100,
  },
  {
    name: 'Page F', uv: 1400, pv: 68, amt: 1700,
  },
];

// Card Chart 1
const cardChartData1 = {
  labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
  datasets: [
    {
      label: 'My First dataset',
      backgroundColor: brandPrimary,
      borderColor: 'rgba(255,255,255,.55)',
      data: [65, 59, 84, 84, 51, 55, 40],
    },
  ],
};

const cardChartOpts1 = {
  tooltips: {
    enabled: false,
    custom: CustomTooltips
  },
  maintainAspectRatio: false,
  legend: {
    display: false,
  },
  scales: {
    xAxes: [
      {
        gridLines: {
          color: 'transparent',
          zeroLineColor: 'transparent',
        },
        ticks: {
          fontSize: 2,
          fontColor: 'transparent',
        },

      }],
    yAxes: [
      {
        display: false,
        ticks: {
          display: false,
          min: Math.min.apply(Math, cardChartData1.datasets[0].data) - 5,
          max: Math.max.apply(Math, cardChartData1.datasets[0].data) + 5,
        },
      }],
  },
  elements: {
    line: {
      borderWidth: 1,
    },
    point: {
      radius: 4,
      hitRadius: 10,
      hoverRadius: 4,
    },
  }
}


// Card Chart 2
const cardChartData2 = {
  labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
  datasets: [
    {
      label: 'My First dataset',
      backgroundColor: brandInfo,
      borderColor: 'rgba(255,255,255,.55)',
      data: [1, 18, 9, 17, 34, 22, 11],
    },
  ],
};

const cardChartOpts2 = {
  tooltips: {
    enabled: false,
    custom: CustomTooltips
  },
  maintainAspectRatio: false,
  legend: {
    display: false,
  },
  scales: {
    xAxes: [
      {
        gridLines: {
          color: 'transparent',
          zeroLineColor: 'transparent',
        },
        ticks: {
          fontSize: 2,
          fontColor: 'transparent',
        },

      }],
    yAxes: [
      {
        display: false,
        ticks: {
          display: false,
          min: Math.min.apply(Math, cardChartData2.datasets[0].data) - 5,
          max: Math.max.apply(Math, cardChartData2.datasets[0].data) + 5,
        },
      }],
  },
  elements: {
    line: {
      tension: 0.00001,
      borderWidth: 1,
    },
    point: {
      radius: 4,
      hitRadius: 10,
      hoverRadius: 4,
    },
  },
};

// Card Chart 3
const cardChartData3 = {
  labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
  datasets: [
    {
      label: 'My First dataset',
      backgroundColor: 'rgba(255,255,255,.2)',
      borderColor: 'rgba(255,255,255,.55)',
      data: [78, 81, 80, 45, 34, 12, 40],
    },
  ],
};

const cardChartOpts3 = {
  tooltips: {
    enabled: false,
    custom: CustomTooltips
  },
  maintainAspectRatio: false,
  legend: {
    display: false,
  },
  scales: {
    xAxes: [
      {
        display: false,
      }],
    yAxes: [
      {
        display: false,
      }],
  },
  elements: {
    line: {
      borderWidth: 2,
    },
    point: {
      radius: 0,
      hitRadius: 10,
      hoverRadius: 4,
    },
  },
};

// Card Chart 4
const cardChartData4 = {
  labels: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
  datasets: [
    {
      label: 'My First dataset',
      backgroundColor: 'rgba(255,255,255,.3)',
      borderColor: 'transparent',
      data: [78, 81, 80, 45, 34, 12, 40, 75, 34, 89, 32, 68, 54, 72, 18, 98],
    },
  ],
};

const cardChartOpts4 = {
  tooltips: {
    enabled: false,
    custom: CustomTooltips
  },
  maintainAspectRatio: false,
  legend: {
    display: false,
  },
  scales: {
    xAxes: [
      {
        display: false,
        barPercentage: 0.6,
      }],
    yAxes: [
      {
        display: false,
      }],
  },
};

// Social Box Chart
const socialBoxData = [
  { data: [65, 59, 84, 84, 51, 55, 40], label: 'facebook' },
  { data: [1, 13, 9, 17, 34, 41, 38], label: 'twitter' },
  { data: [78, 81, 80, 45, 34, 12, 40], label: 'linkedin' },
  { data: [35, 23, 56, 22, 97, 23, 64], label: 'google' },
];

const makeSocialBoxData = (dataSetNo) => {
  const dataset = socialBoxData[dataSetNo];
  const data = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
      {
        backgroundColor: 'rgba(255,255,255,.1)',
        borderColor: 'rgba(255,255,255,.55)',
        pointHoverBackgroundColor: '#fff',
        borderWidth: 2,
        data: dataset.data,
        label: dataset.label,
      },
    ],
  };
  return () => data;
};

const socialChartOpts = {
  tooltips: {
    enabled: false,
    custom: CustomTooltips
  },
  responsive: true,
  maintainAspectRatio: false,
  legend: {
    display: false,
  },
  scales: {
    xAxes: [
      {
        display: false,
      }],
    yAxes: [
      {
        display: false,
      }],
  },
  elements: {
    point: {
      radius: 0,
      hitRadius: 10,
      hoverRadius: 4,
      hoverBorderWidth: 3,
    },
  },
};

// sparkline charts
const sparkLineChartData = [
  {
    data: [35, 23, 56, 22, 97, 23, 64],
    label: 'New Clients',
  },
  {
    data: [65, 59, 84, 84, 51, 55, 40],
    label: 'Recurring Clients',
  },
  {
    data: [35, 23, 56, 22, 97, 23, 64],
    label: 'Pageviews',
  },
  {
    data: [65, 59, 84, 84, 51, 55, 40],
    label: 'Organic',
  },
  {
    data: [78, 81, 80, 45, 34, 12, 40],
    label: 'CTR',
  },
  {
    data: [1, 13, 9, 17, 34, 41, 38],
    label: 'Bounce Rate',
  },
];

const makeSparkLineData = (dataSetNo, variant) => {
  const dataset = sparkLineChartData[dataSetNo];
  const data = {
    labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
    datasets: [
      {
        backgroundColor: 'transparent',
        borderColor: variant ? variant : '#c2cfd6',
        data: dataset.data,
        label: dataset.label,
      },
    ],
  };
  return () => data;
};

const sparklineChartOpts = {
  tooltips: {
    enabled: false,
    custom: CustomTooltips
  },
  responsive: true,
  maintainAspectRatio: true,
  scales: {
    xAxes: [
      {
        display: false,
      }],
    yAxes: [
      {
        display: false,
      }],
  },
  elements: {
    line: {
      borderWidth: 2,
    },
    point: {
      radius: 0,
      hitRadius: 10,
      hoverRadius: 4,
      hoverBorderWidth: 3,
    },
  },
  legend: {
    display: false,
  },
};

// Main Chart

//Random Numbers
function random(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

var elements = 27;
var data1 = [];
var data2 = [];
var data3 = [];

for (var i = 0; i <= elements; i++) {
  data1.push(random(50, 200));
  data2.push(random(80, 100));
  data3.push(65);
}

const mainChart = {
  labels: ['Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'],
  datasets: [
    {
      label: 'My First dataset',
      backgroundColor: hexToRgba(brandInfo, 10),
      borderColor: brandInfo,
      pointHoverBackgroundColor: '#fff',
      borderWidth: 2,
      data: data1,
    },
    {
      label: 'My Second dataset',
      backgroundColor: 'transparent',
      borderColor: brandSuccess,
      pointHoverBackgroundColor: '#fff',
      borderWidth: 2,
      data: data2,
    },
    {
      label: 'My Third dataset',
      backgroundColor: 'transparent',
      borderColor: brandDanger,
      pointHoverBackgroundColor: '#fff',
      borderWidth: 1,
      borderDash: [8, 5],
      data: data3,
    },
  ],
};

const mainChartOpts = {
  tooltips: {
    enabled: false,
    custom: CustomTooltips,
    intersect: true,
    mode: 'index',
    position: 'nearest',
    callbacks: {
      labelColor: function (tooltipItem, chart) {
        return { backgroundColor: chart.data.datasets[tooltipItem.datasetIndex].borderColor }
      }
    }
  },
  maintainAspectRatio: false,
  legend: {
    display: false,
  },
  scales: {
    xAxes: [
      {
        gridLines: {
          drawOnChartArea: false,
        },
      }],
    yAxes: [
      {
        ticks: {
          beginAtZero: true,
          maxTicksLimit: 5,
          stepSize: Math.ceil(250 / 5),
          max: 250,
        },
      }],
  },
  elements: {
    point: {
      radius: 0,
      hitRadius: 10,
      hoverRadius: 4,
      hoverBorderWidth: 3,
    },
  },
};

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      dropdownOpen: false,
      radioSelected: 2,
      time: "0",
      memory: {
        used: 0,
        max: 0,
        usedNonHeap: 0,
        maxNonHeap: 0,
        threadCount: 0,
        daemonThread: 0,
        startedThread: 0,
        peakThread: 0
      },
      
    };

    this.toggle = this.toggle.bind(this);
    this.onRadioBtnClick = this.onRadioBtnClick.bind(this);
    this.updateMemoryState = this.updateMemoryState.bind(this);
    this.eventSource = new EventSource("http://localhost:8888/jwt/event/performance");
    this.eventSourceTiming = new EventSource("http://localhost:8888/jwt/event/timing");
  }

  toggle() {
    this.setState({
      dropdownOpen: !this.state.dropdownOpen,
    });
  }

  onRadioBtnClick(radioSelected) {
    this.setState({
      radioSelected: radioSelected,
    });
  }

  updateMemoryState(memory) {
    this.setState({ memory: memory });
  }

  componentWillMount() {
    console.log('componentWillMount')

  }

  componentWillUnmount() {
    console.log('componentWillUnmount')
    this.eventSource.close();
    this.eventSourceTiming.close();
  }

  componentDidMount() {
    console.log('componentDidMount');

    this.eventSource.addEventListener('message', response => {
      let data = JSON.parse(response.data +'');
      this.updateMemoryState({
        used: data.usedMemory,
        max: data.maxMemory,
        usedNonHeap: data.usedMemoryNonHeap,
        maxNonHeap: data.maxMemoryNonHeap,
        threadCount: data.threadCount,
        daemonThread: data.daemonThread,
        startedThread: data.startedThread,
        peakThread: data.peakThread
      });
    }, false);

    // this.eventSource.onmessage = e => {
    //   let data = JSON.parse(e.data+'');
    //   this.updateMemoryState({
    //     used: data.usedMemory,
    //     max: data.maxMemory,
    //     usedNonHeap: data.usedMemoryNonHeap,
    //     maxNonHeap: data.maxMemoryNonHeap,
    //     threadCount: data.threadCount,
    //     daemonThread: data.daemonThread,
    //     startedThread: data.startedThread,
    //     peakThread: data.peakThread
    //   });
    // };

    this.eventSourceTiming.addEventListener('message', response => {
      let data = response.data+'';
      this.setState({ time: data });
    }, false);

    // this.eventSourceTiming.onmessage = e => {
    //   let data = '' + e.data;
    //   this.setState({ time: data });
    // };

  }

  loading = () => <div className="animated fadeIn pt-1 text-center">Loading...</div>

  render() {

    console.log('render')

    const { memory, time } = this.state;

    let usagePercen = ((memory.used / memory.max) * 100).toFixed(2);
    let usagePercenNonHeap = ((memory.usedNonHeap / memory.max) * 100).toFixed(2);

    return (
      <div className="animated fadeIn">

        <Row>
          <Col xs="12" sm="6" lg="3">
            <Widget01
              color="primary"
              variant="inverse"
              value={usagePercen}
              header={`Heap ${usagePercen} %`}
              mainText1={`Usage : ${memory.used}`}
              smallText={`Memory usage of the heap (${memory.max})`} />
          </Col>
          <Col xs="12" sm="6" lg="3">
            <Widget01
              color="info"
              variant="inverse"
              value={usagePercenNonHeap}
              header={`Non Heap ${usagePercenNonHeap} %`}
              mainText1={`Usage : ${memory.usedNonHeap}`}
              smallText={`Memory usage of the non heap (${memory.max})`} />
          </Col>
          <Col xs="12" sm="6" lg="3">
            <Widget02
              header={memory.threadCount}
              peakThread={memory.peakThread}
              daemonThread={memory.daemonThread}
              mainText="Count Thread"
              icon="fa fa-bell"
              color="danger" />
          </Col>

          <Col xs="12" sm="6" lg="3">
            <Widget04 icon="icon-speedometer" color="info" header={time} value="25">Server Time</Widget04>
          </Col>
        </Row>
        <Row>
          <Col md="6">
            <Card>
              <CardBody>
                <div style={{ width: '100%', height: 300 }}>
                  <ResponsiveContainer>
                    <ComposedChart
                      width={500}
                      height={400}
                      data={data}
                      margin={{
                        top: 20, right: 0, bottom: 20, left: 0,
                      }}
                    >
                      <CartesianGrid stroke="#f5f5f5" />
                      <XAxis dataKey="name" />
                      <YAxis />
                      <Tooltip />
                      <Legend />
                      <Area type="monotone" dataKey="amt" fill="#8884d8" stroke="#8884d8" />
                      <Bar dataKey="pv" barSize={20} fill="#413ea0" />
                      <Line2 type="monotone" dataKey="uv" stroke="#ff7300" />
                    </ComposedChart>
                  </ResponsiveContainer>
                </div>
              </CardBody>
            </Card>
          </Col>
          <Col md="6">
            {/* <Card>
              <CardBody>

              </CardBody>
            </Card> */}
          </Col>
        </Row>

        <Row>
          <Col>
            <Card>
              <CardBody>
                <Row>
                  <Col sm="5">
                    <CardTitle className="mb-0">Traffic</CardTitle>
                    <div className="small text-muted">November 2015</div>
                  </Col>
                  <Col sm="7" className="d-none d-sm-inline-block">
                    <Button color="primary" className="float-right"><i className="icon-cloud-download"></i></Button>
                    <ButtonToolbar className="float-right" aria-label="Toolbar with button groups">
                      <ButtonGroup className="mr-3" aria-label="First group">
                        <Button color="outline-secondary" onClick={() => this.onRadioBtnClick(1)} active={this.state.radioSelected === 1}>Day</Button>
                        <Button color="outline-secondary" onClick={() => this.onRadioBtnClick(2)} active={this.state.radioSelected === 2}>Month</Button>
                        <Button color="outline-secondary" onClick={() => this.onRadioBtnClick(3)} active={this.state.radioSelected === 3}>Year</Button>
                      </ButtonGroup>
                    </ButtonToolbar>
                  </Col>
                </Row>
                <div className="chart-wrapper" style={{ height: 300 + 'px', marginTop: 40 + 'px' }}>
                  <Line data={mainChart} options={mainChartOpts} height={300} />
                </div>
              </CardBody>
              <CardFooter>
                <Row className="text-center">
                  <Col sm={12} md className="mb-sm-2 mb-0">
                    <div className="text-muted">Visits</div>
                    <strong>29.703 Users (40%)</strong>
                    <Progress className="progress-xs mt-2" color="success" value="40" />
                  </Col>
                  <Col sm={12} md className="mb-sm-2 mb-0 d-md-down-none">
                    <div className="text-muted">Unique</div>
                    <strong>24.093 Users (20%)</strong>
                    <Progress className="progress-xs mt-2" color="info" value="20" />
                  </Col>
                  <Col sm={12} md className="mb-sm-2 mb-0">
                    <div className="text-muted">Pageviews</div>
                    <strong>78.706 Views (60%)</strong>
                    <Progress className="progress-xs mt-2" color="warning" value="60" />
                  </Col>
                  <Col sm={12} md className="mb-sm-2 mb-0">
                    <div className="text-muted">New Users</div>
                    <strong>22.123 Users (80%)</strong>
                    <Progress className="progress-xs mt-2" color="danger" value="80" />
                  </Col>
                  <Col sm={12} md className="mb-sm-2 mb-0 d-md-down-none">
                    <div className="text-muted">Bounce Rate</div>
                    <strong>Average Rate (40.15%)</strong>
                    <Progress className="progress-xs mt-2" color="primary" value="40" />
                  </Col>
                </Row>
              </CardFooter>
            </Card>
          </Col>
        </Row>

        <Row>
          <Col xs="6" sm="6" lg="3">
            <Suspense fallback={this.loading()}>
              <Widget03 dataBox={() => ({ variant: 'facebook', friends: '89k', feeds: '459' })} >
                <div className="chart-wrapper">
                  <Line data={makeSocialBoxData(0)} options={socialChartOpts} height={90} />
                </div>
              </Widget03>
            </Suspense>
          </Col>

          <Col xs="6" sm="6" lg="3">
            <Suspense fallback={this.loading()}>
              <Widget03 dataBox={() => ({ variant: 'twitter', followers: '973k', tweets: '1.792' })} >
                <div className="chart-wrapper">
                  <Line data={makeSocialBoxData(1)} options={socialChartOpts} height={90} />
                </div>
              </Widget03>
            </Suspense>
          </Col>

          <Col xs="6" sm="6" lg="3">
            <Suspense fallback={this.loading()}>
              <Widget03 dataBox={() => ({ variant: 'linkedin', contacts: '500+', feeds: '292' })} >
                <div className="chart-wrapper">
                  <Line data={makeSocialBoxData(2)} options={socialChartOpts} height={90} />
                </div>
              </Widget03>
            </Suspense>
          </Col>

          <Col xs="6" sm="6" lg="3">
            <Suspense fallback={this.loading()}>
              <Widget03 dataBox={() => ({ variant: 'google-plus', followers: '894', circles: '92' })} >
                <div className="chart-wrapper">
                  <Line data={makeSocialBoxData(3)} options={socialChartOpts} height={90} />
                </div>
              </Widget03>
            </Suspense>
          </Col>
        </Row>

        <Row>
          <Col>
            <Card>
              <CardHeader>
                Traffic {' & '} Sales
              </CardHeader>
              <CardBody>
                <Row>
                  <Col xs="12" md="6" xl="6">
                    <Row>
                      <Col sm="6">
                        <div className="callout callout-info">
                          <small className="text-muted">New Clients</small>
                          <br />
                          <strong className="h4">9,123</strong>
                          <div className="chart-wrapper">
                            <Line data={makeSparkLineData(0, brandPrimary)} options={sparklineChartOpts} width={100} height={30} />
                          </div>
                        </div>
                      </Col>
                      <Col sm="6">
                        <div className="callout callout-danger">
                          <small className="text-muted">Recurring Clients</small>
                          <br />
                          <strong className="h4">22,643</strong>
                          <div className="chart-wrapper">
                            <Line data={makeSparkLineData(1, brandDanger)} options={sparklineChartOpts} width={100} height={30} />
                          </div>
                        </div>
                      </Col>
                    </Row>
                    <hr className="mt-0" />
                    <div className="progress-group mb-4">
                      <div className="progress-group-prepend">
                        <span className="progress-group-text">
                          Monday
                        </span>
                      </div>
                      <div className="progress-group-bars">
                        <Progress className="progress-xs" color="info" value="34" />
                        <Progress className="progress-xs" color="danger" value="78" />
                      </div>
                    </div>
                    <div className="progress-group mb-4">
                      <div className="progress-group-prepend">
                        <span className="progress-group-text">
                          Tuesday
                        </span>
                      </div>
                      <div className="progress-group-bars">
                        <Progress className="progress-xs" color="info" value="56" />
                        <Progress className="progress-xs" color="danger" value="94" />
                      </div>
                    </div>
                    <div className="progress-group mb-4">
                      <div className="progress-group-prepend">
                        <span className="progress-group-text">
                          Wednesday
                        </span>
                      </div>
                      <div className="progress-group-bars">
                        <Progress className="progress-xs" color="info" value="12" />
                        <Progress className="progress-xs" color="danger" value="67" />
                      </div>
                    </div>
                    <div className="progress-group mb-4">
                      <div className="progress-group-prepend">
                        <span className="progress-group-text">
                          Thursday
                        </span>
                      </div>
                      <div className="progress-group-bars">
                        <Progress className="progress-xs" color="info" value="43" />
                        <Progress className="progress-xs" color="danger" value="91" />
                      </div>
                    </div>
                    <div className="progress-group mb-4">
                      <div className="progress-group-prepend">
                        <span className="progress-group-text">
                          Friday
                        </span>
                      </div>
                      <div className="progress-group-bars">
                        <Progress className="progress-xs" color="info" value="22" />
                        <Progress className="progress-xs" color="danger" value="73" />
                      </div>
                    </div>
                    <div className="progress-group mb-4">
                      <div className="progress-group-prepend">
                        <span className="progress-group-text">
                          Saturday
                        </span>
                      </div>
                      <div className="progress-group-bars">
                        <Progress className="progress-xs" color="info" value="53" />
                        <Progress className="progress-xs" color="danger" value="82" />
                      </div>
                    </div>
                    <div className="progress-group mb-4">
                      <div className="progress-group-prepend">
                        <span className="progress-group-text">
                          Sunday
                        </span>
                      </div>
                      <div className="progress-group-bars">
                        <Progress className="progress-xs" color="info" value="9" />
                        <Progress className="progress-xs" color="danger" value="69" />
                      </div>
                    </div>
                    <div className="legend text-center">
                      <small>
                        <sup className="px-1"><Badge pill color="info">&nbsp;</Badge></sup>
                        New clients
                        &nbsp;
                        <sup className="px-1"><Badge pill color="danger">&nbsp;</Badge></sup>
                        Recurring clients
                      </small>
                    </div>
                  </Col>
                  <Col xs="12" md="6" xl="6">
                    <Row>
                      <Col sm="6">
                        <div className="callout callout-warning">
                          <small className="text-muted">Pageviews</small>
                          <br />
                          <strong className="h4">78,623</strong>
                          <div className="chart-wrapper">
                            <Line data={makeSparkLineData(2, brandWarning)} options={sparklineChartOpts} width={100} height={30} />
                          </div>
                        </div>
                      </Col>
                      <Col sm="6">
                        <div className="callout callout-success">
                          <small className="text-muted">Organic</small>
                          <br />
                          <strong className="h4">49,123</strong>
                          <div className="chart-wrapper">
                            <Line data={makeSparkLineData(3, brandSuccess)} options={sparklineChartOpts} width={100} height={30} />
                          </div>
                        </div>
                      </Col>
                    </Row>
                    <hr className="mt-0" />
                    <ul>
                      <div className="progress-group">
                        <div className="progress-group-header">
                          <i className="icon-user progress-group-icon"></i>
                          <span className="title">Male</span>
                          <span className="ml-auto font-weight-bold">43%</span>
                        </div>
                        <div className="progress-group-bars">
                          <Progress className="progress-xs" color="warning" value="43" />
                        </div>
                      </div>
                      <div className="progress-group mb-5">
                        <div className="progress-group-header">
                          <i className="icon-user-female progress-group-icon"></i>
                          <span className="title">Female</span>
                          <span className="ml-auto font-weight-bold">37%</span>
                        </div>
                        <div className="progress-group-bars">
                          <Progress className="progress-xs" color="warning" value="37" />
                        </div>
                      </div>
                      <div className="progress-group">
                        <div className="progress-group-header">
                          <i className="icon-globe progress-group-icon"></i>
                          <span className="title">Organic Search</span>
                          <span className="ml-auto font-weight-bold">191,235 <span className="text-muted small">(56%)</span></span>
                        </div>
                        <div className="progress-group-bars">
                          <Progress className="progress-xs" color="success" value="56" />
                        </div>
                      </div>
                      <div className="progress-group">
                        <div className="progress-group-header">
                          <i className="icon-social-facebook progress-group-icon"></i>
                          <span className="title">Facebook</span>
                          <span className="ml-auto font-weight-bold">51,223 <span className="text-muted small">(15%)</span></span>
                        </div>
                        <div className="progress-group-bars">
                          <Progress className="progress-xs" color="success" value="15" />
                        </div>
                      </div>
                      <div className="progress-group">
                        <div className="progress-group-header">
                          <i className="icon-social-twitter progress-group-icon"></i>
                          <span className="title">Twitter</span>
                          <span className="ml-auto font-weight-bold">37,564 <span className="text-muted small">(11%)</span></span>
                        </div>
                        <div className="progress-group-bars">
                          <Progress className="progress-xs" color="success" value="11" />
                        </div>
                      </div>
                      <div className="progress-group">
                        <div className="progress-group-header">
                          <i className="icon-social-linkedin progress-group-icon"></i>
                          <span className="title">LinkedIn</span>
                          <span className="ml-auto font-weight-bold">27,319 <span className="text-muted small">(8%)</span></span>
                        </div>
                        <div className="progress-group-bars">
                          <Progress className="progress-xs" color="success" value="8" />
                        </div>
                      </div>
                      <div className="divider text-center">
                        <Button color="link" size="sm" className="text-muted" data-toggle="tooltip" data-placement="top"
                          title="" data-original-title="show more"><i className="icon-options"></i></Button>
                      </div>
                    </ul>
                  </Col>
                </Row>
                <br />
                <Table hover responsive className="table-outline mb-0 d-none d-sm-table">
                  <thead className="thead-light">
                    <tr>
                      <th className="text-center"><i className="icon-people"></i></th>
                      <th>User</th>
                      <th className="text-center">Country</th>
                      <th>Usage</th>
                      <th className="text-center">Payment Method</th>
                      <th>Activity</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td className="text-center">
                        <div className="avatar">
                          <img src={'assets/img/avatars/1.jpg'} className="img-avatar" alt="admin@bootstrapmaster.com" />
                          <span className="avatar-status badge-success"></span>
                        </div>
                      </td>
                      <td>
                        <div>Yiorgos Avraamu</div>
                        <div className="small text-muted">
                          <span>New</span> | Registered: Jan 1, 2015
                      </div>
                      </td>
                      <td className="text-center">
                        <i className="flag-icon flag-icon-us h4 mb-0" title="us" id="us"></i>
                      </td>
                      <td>
                        <div className="clearfix">
                          <div className="float-left">
                            <strong>50%</strong>
                          </div>
                          <div className="float-right">
                            <small className="text-muted">Jun 11, 2015 - Jul 10, 2015</small>
                          </div>
                        </div>
                        <Progress className="progress-xs" color="success" value="50" />
                      </td>
                      <td className="text-center">
                        <i className="fa fa-cc-mastercard" style={{ fontSize: 24 + 'px' }}></i>
                      </td>
                      <td>
                        <div className="small text-muted">Last login</div>
                        <strong>10 sec ago</strong>
                      </td>
                    </tr>
                    <tr>
                      <td className="text-center">
                        <div className="avatar">
                          <img src={'assets/img/avatars/2.jpg'} className="img-avatar" alt="admin@bootstrapmaster.com" />
                          <span className="avatar-status badge-danger"></span>
                        </div>
                      </td>
                      <td>
                        <div>Avram Tarasios</div>
                        <div className="small text-muted">

                          <span>Recurring</span> | Registered: Jan 1, 2015
                      </div>
                      </td>
                      <td className="text-center">
                        <i className="flag-icon flag-icon-br h4 mb-0" title="br" id="br"></i>
                      </td>
                      <td>
                        <div className="clearfix">
                          <div className="float-left">
                            <strong>10%</strong>
                          </div>
                          <div className="float-right">
                            <small className="text-muted">Jun 11, 2015 - Jul 10, 2015</small>
                          </div>
                        </div>
                        <Progress className="progress-xs" color="info" value="10" />
                      </td>
                      <td className="text-center">
                        <i className="fa fa-cc-visa" style={{ fontSize: 24 + 'px' }}></i>
                      </td>
                      <td>
                        <div className="small text-muted">Last login</div>
                        <strong>5 minutes ago</strong>
                      </td>
                    </tr>
                    <tr>
                      <td className="text-center">
                        <div className="avatar">
                          <img src={'assets/img/avatars/3.jpg'} className="img-avatar" alt="admin@bootstrapmaster.com" />
                          <span className="avatar-status badge-warning"></span>
                        </div>
                      </td>
                      <td>
                        <div>Quintin Ed</div>
                        <div className="small text-muted">
                          <span>New</span> | Registered: Jan 1, 2015
                      </div>
                      </td>
                      <td className="text-center">
                        <i className="flag-icon flag-icon-in h4 mb-0" title="in" id="in"></i>
                      </td>
                      <td>
                        <div className="clearfix">
                          <div className="float-left">
                            <strong>74%</strong>
                          </div>
                          <div className="float-right">
                            <small className="text-muted">Jun 11, 2015 - Jul 10, 2015</small>
                          </div>
                        </div>
                        <Progress className="progress-xs" color="warning" value="74" />
                      </td>
                      <td className="text-center">
                        <i className="fa fa-cc-stripe" style={{ fontSize: 24 + 'px' }}></i>
                      </td>
                      <td>
                        <div className="small text-muted">Last login</div>
                        <strong>1 hour ago</strong>
                      </td>
                    </tr>
                    <tr>
                      <td className="text-center">
                        <div className="avatar">
                          <img src={'assets/img/avatars/4.jpg'} className="img-avatar" alt="admin@bootstrapmaster.com" />
                          <span className="avatar-status badge-secondary"></span>
                        </div>
                      </td>
                      <td>
                        <div>Enéas Kwadwo</div>
                        <div className="small text-muted">
                          <span>New</span> | Registered: Jan 1, 2015
                      </div>
                      </td>
                      <td className="text-center">
                        <i className="flag-icon flag-icon-fr h4 mb-0" title="fr" id="fr"></i>
                      </td>
                      <td>
                        <div className="clearfix">
                          <div className="float-left">
                            <strong>98%</strong>
                          </div>
                          <div className="float-right">
                            <small className="text-muted">Jun 11, 2015 - Jul 10, 2015</small>
                          </div>
                        </div>
                        <Progress className="progress-xs" color="danger" value="98" />
                      </td>
                      <td className="text-center">
                        <i className="fa fa-paypal" style={{ fontSize: 24 + 'px' }}></i>
                      </td>
                      <td>
                        <div className="small text-muted">Last login</div>
                        <strong>Last month</strong>
                      </td>
                    </tr>
                    <tr>
                      <td className="text-center">
                        <div className="avatar">
                          <img src={'assets/img/avatars/5.jpg'} className="img-avatar" alt="admin@bootstrapmaster.com" />
                          <span className="avatar-status badge-success"></span>
                        </div>
                      </td>
                      <td>
                        <div>Agapetus Tadeáš</div>
                        <div className="small text-muted">
                          <span>New</span> | Registered: Jan 1, 2015
                      </div>
                      </td>
                      <td className="text-center">
                        <i className="flag-icon flag-icon-es h4 mb-0" title="es" id="es"></i>
                      </td>
                      <td>
                        <div className="clearfix">
                          <div className="float-left">
                            <strong>22%</strong>
                          </div>
                          <div className="float-right">
                            <small className="text-muted">Jun 11, 2015 - Jul 10, 2015</small>
                          </div>
                        </div>
                        <Progress className="progress-xs" color="info" value="22" />
                      </td>
                      <td className="text-center">
                        <i className="fa fa-google-wallet" style={{ fontSize: 24 + 'px' }}></i>
                      </td>
                      <td>
                        <div className="small text-muted">Last login</div>
                        <strong>Last week</strong>
                      </td>
                    </tr>
                    <tr>
                      <td className="text-center">
                        <div className="avatar">
                          <img src={'assets/img/avatars/6.jpg'} className="img-avatar" alt="admin@bootstrapmaster.com" />
                          <span className="avatar-status badge-danger"></span>
                        </div>
                      </td>
                      <td>
                        <div>Friderik Dávid</div>
                        <div className="small text-muted">
                          <span>New</span> | Registered: Jan 1, 2015
                      </div>
                      </td>
                      <td className="text-center">
                        <i className="flag-icon flag-icon-pl h4 mb-0" title="pl" id="pl"></i>
                      </td>
                      <td>
                        <div className="clearfix">
                          <div className="float-left">
                            <strong>43%</strong>
                          </div>
                          <div className="float-right">
                            <small className="text-muted">Jun 11, 2015 - Jul 10, 2015</small>
                          </div>
                        </div>
                        <Progress className="progress-xs" color="success" value="43" />
                      </td>
                      <td className="text-center">
                        <i className="fa fa-cc-amex" style={{ fontSize: 24 + 'px' }}></i>
                      </td>
                      <td>
                        <div className="small text-muted">Last login</div>
                        <strong>Yesterday</strong>
                      </td>
                    </tr>
                  </tbody>
                </Table>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}

export default Dashboard;
